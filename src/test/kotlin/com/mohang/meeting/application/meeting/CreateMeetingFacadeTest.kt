package com.mohang.meeting.application.meeting

import com.mohang.meeting.application.applyform.CreateApplyFormUseCase
import com.mohang.meeting.application.meeting.exception.NoAuthorityCreateMeeting
import com.mohang.meeting.application.meetingrole.CreateMeetingRoleUseCase
import com.mohang.meeting.application.member.TakeMemberDataUseCase
import com.mohang.meeting.application.participant.RegisterParticipantUseCase
import com.mohang.meeting.configuration.client.exception.FeignClientException
import com.mohang.meeting.configuration.exception.ExceptionResponse
import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.domain.meetingrole.enums.MeetingAuthority
import com.mohang.meeting.fixture.ApplyFormFixture.createApplyFormDto
import com.mohang.meeting.fixture.MeetingFixture.createMeetingDto
import com.mohang.meeting.fixture.MemberDataFixture.memberData
import com.mohang.meeting.fixture.ParticipantFixture.participant
import com.mohang.meeting.infrastructure.client.member.model.Role
import com.mohang.meeting.infrastructure.eventproducer.EventProducer
import com.mohang.meeting.mock.MockTransactionTemplate
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

/**
 * Created by ShinD on 2022/09/08.
 */
internal class CreateMeetingFacadeTet {


    /**
     * 모임 생성
     *
     * feignclient로 생성하려는 회원 정보 받아오기
     * 회원 정보 못 받아온 경우 예외
     *
     * 생성하려는 회원이 블랙리스트인 경우 생성 실패
     *
     * 모임 생성 시 모임 역할과 가입 신청 양식도 같이 생성되야 함.
     *
     * 모임 생성 시 최초로 사용되는 가입 신청 양식의 isCurrentlyUsed는 true여야 함
     *
     * 모임 생성 시 최초로 정해지는 역할은 REPRESENTATIVE와 BASIC 두개
     *
     * APPLY FORM 저장
     *
     *
     * 생성한 회원은 대표 참가자가 되어야 함
     *
     *
     * 모임 생성 시 모임 생성 이벤트 발행
     * (아직은 구현하지 않았고, 이후 카프카로 진행할 예정)
     */

    private val takeMemberDataUseCase = mockkClass(TakeMemberDataUseCase::class)
    private val createMeetingUseCase = mockkClass(CreateMeetingUseCase::class)
    private val createMeetingRoleUseCase = mockkClass(CreateMeetingRoleUseCase::class)
    private val registerParticipantUseCase = mockkClass(RegisterParticipantUseCase::class)
    private val createApplyFormUseCase = mockkClass(CreateApplyFormUseCase::class)
    private val eventProducer = mockkClass(EventProducer::class)
    private val transaction = MockTransactionTemplate()

    private val createMeetingFacade by lazy {
        CreateMeetingFacade(
           takeMemberDataUseCase = takeMemberDataUseCase,
           createMeetingUseCase = createMeetingUseCase,
           createMeetingRoleUseCase = createMeetingRoleUseCase,
           registerParticipantUseCase = registerParticipantUseCase,
           createApplyFormUseCase = createApplyFormUseCase,
           eventProducer = eventProducer,
           transaction = transaction,
        )
    }


    private fun registerRepresentativeRole(meetingId: Long) =
        MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = meetingId)

    private fun registerBasicRole(meetingId: Long) =
        MeetingRole(name = "일반", authority = MeetingAuthority.BASIC, meetingId = meetingId)


    @Test
    fun `모임 생성 성공`() {

        //given
        val memberId = 1L
        val createMeetingDto = createMeetingDto()
        val createApplyFormDto = createApplyFormDto(applyFormName = "신청 양식 예시", listOf("이름", "나이", "사는곳"))
        val meetingId = 10L
        val memberData = memberData(id = memberId)


        val basicRole = MeetingRole.basicRole(meetingId)
        val savedRepresentativeRole =MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = meetingId)
        val representRoleId = 10L
        ReflectionTestUtils.setField(savedRepresentativeRole, "id", representRoleId)


        every { takeMemberDataUseCase.command(memberId) } returns memberData
        every { createMeetingUseCase.command(createMeetingDto) } returns meetingId
        every { createMeetingRoleUseCase.command(any()) } returns listOf(savedRepresentativeRole, basicRole)
        every { registerParticipantUseCase.command(memberData, meetingId, representRoleId) } returns participant(memberId = memberId, meetingId = meetingId, meetingRoleId = representRoleId)
        every { createApplyFormUseCase.command(createApplyFormDto, meetingId) } returns 1L
        every { eventProducer.send(any(), meetingId) } just runs



        //then
        expectThat(
            createMeetingFacade.create(memberId, createMeetingDto, createApplyFormDto)
        ) {
            isEqualTo(meetingId)
        }
        verify (exactly = 1){ takeMemberDataUseCase.command(memberId) }
        verify (exactly = 1){ createMeetingUseCase.command(createMeetingDto) }
        verify (exactly = 1){ createMeetingRoleUseCase.command(any()) }
        verify (exactly = 1){ registerParticipantUseCase.command(memberData, meetingId, representRoleId) }
        verify (exactly = 1){ createApplyFormUseCase.command(createApplyFormDto, meetingId) }
        verify (exactly = 1){ eventProducer.send(any(), meetingId) }
    }

    @Test
    fun `모임 생성 실패 - feignClient 서버 에러`() {
        //given
        val memberId = 1L
        val createMeetingDto = createMeetingDto()
        val createApplyFormDto = createApplyFormDto(applyFormName = "신청 양식 예시", listOf("이름", "나이", "사는곳"))

        every { takeMemberDataUseCase.command(memberId) } throws FeignClientException(ExceptionResponse(999, "sample exception"))

        //when, then
        expectThrows<FeignClientException> {
            createMeetingFacade.create(
                memberId = memberId,
                createMeetingDto = createMeetingDto,
                createApplyFormDto = createApplyFormDto
            )
        }
    }

    @Test
    fun `모임 생성 실패 - 생성하려는 사람이 블랙리스트인 경우`() {
        //given
        val memberId = 1L
        val createMeetingDto = createMeetingDto()
        val createApplyFormDto = createApplyFormDto(applyFormName = "신청 양식 예시", listOf("이름", "나이", "사는곳"))
        val memberData = memberData(id = memberId, role = Role.BLACK)

        every { takeMemberDataUseCase.command(memberId) } returns memberData

        //when, then
        expectThrows<NoAuthorityCreateMeeting> {
            createMeetingFacade.create(
                memberId = memberId,
                createMeetingDto = createMeetingDto,
                createApplyFormDto = createApplyFormDto
            )
        }
    }
}