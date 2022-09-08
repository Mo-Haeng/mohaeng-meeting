package com.mohang.meeting.application.meeting

import com.mohang.meeting.application.meeting.exception.NotAuthorityCreateMeeting
import com.mohang.meeting.configuration.client.exception.FeignClientException
import com.mohang.meeting.configuration.exception.ExceptionResponse
import com.mohang.meeting.domain.enums.MeetingAuthority
import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.domain.participant.Participant
import com.mohang.meeting.fixture.ApplyFormFixture
import com.mohang.meeting.fixture.FeignClientFixture
import com.mohang.meeting.fixture.MeetingFixture
import com.mohang.meeting.fixture.MeetingFixture.savedMeeting
import com.mohang.meeting.domain.member.MemberServiceClient
import com.mohang.meeting.infrastructure.client.member.model.Role
import com.mohang.meeting.infrastructure.eventproducer.EventProducer
import com.mohang.meeting.infrastructure.persistence.applyform.ApplyFormRepository
import com.mohang.meeting.infrastructure.persistence.meeting.MeetingRepository
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import com.mohang.meeting.infrastructure.persistence.participant.ParticipantRepository
import com.mohang.meeting.mock.MockTransactionTemplate
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo

/**
 * Created by ShinD on 2022/09/07.
 */
internal class CreateMeetingUseCaseTest {

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

    private val meetingRepository = mockkClass(MeetingRepository::class)
    private val meetingRoleRepository = mockkClass(MeetingRoleRepository::class)
    private val participantRepository = mockkClass(ParticipantRepository::class)
    private val applyFormRepository = mockkClass(ApplyFormRepository::class)
    private val memberServiceClient = mockkClass(MemberServiceClient::class) //FeignClient

    private val eventProducer = mockkClass(EventProducer::class)

    private val transaction = MockTransactionTemplate()

    private val createMeetingUseCase: CreateMeetingUseCase by lazy {
        CreateMeetingUseCase(
            meetingRepository = meetingRepository,
            meetingRoleRepository = meetingRoleRepository,
            participantRepository = participantRepository,
            applyFormRepository = applyFormRepository,
            memberServiceClient = memberServiceClient,
            transaction = transaction,
            eventProducer = eventProducer
        )
    }

    private fun registerRepresentativeRole(meetingId: Long) =
        MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = meetingId)

    private fun registerBasicRole(meetingId: Long) =
        MeetingRole(name = "일반", authority = MeetingAuthority.BASIC, meetingId = meetingId)


    @Test
    fun `모임 생성 성공`() {

        //given
        val savedMeetingId = 10L
        val memberId = 1L
        val savedMeeting = savedMeeting(id = savedMeetingId)
        val applyFormFieldNames = listOf("이름", "나이", "성별")
        val applyFormName = "2022년 2학기 가입 신청서 양식"
        val createMeetingDto = MeetingFixture.createMeetingDto()
        val createApplyFormDto = ApplyFormFixture.createApplyFormDto(applyFormName, applyFormFieldNames)

        val representativeRole = registerRepresentativeRole(savedMeeting.id!!)
        ReflectionTestUtils.setField(representativeRole,"id", 1L)
        val basicRole = registerBasicRole(savedMeeting.id!!)

        every { meetingRepository.save(any()) } returns savedMeeting
        every { memberServiceClient.getMember(memberId) } returns FeignClientFixture.memberData()
        every { meetingRoleRepository.saveAll<MeetingRole>(any()) } returns listOf(representativeRole, basicRole)
        every { participantRepository.save(any()) } returns Participant(1L, "", "", 1L, 1L)
        every { applyFormRepository.save(any()) } returns createApplyFormDto.toEntity(savedMeetingId)
        every { eventProducer.send(any(), any()) } just runs


        //when
        val meetingId = createMeetingUseCase.command(memberId, createMeetingDto, createApplyFormDto)

        //then
        expectThat(meetingId) {
            isEqualTo(savedMeetingId)
        }
        verify (exactly = 1) { meetingRepository.save(any()) }
        verify (exactly = 1) { memberServiceClient.getMember(memberId) }
        verify (exactly = 1) { meetingRoleRepository.saveAll<MeetingRole>(any()) }
        verify (exactly = 1) { participantRepository.save(any()) }
        verify (exactly = 1) { applyFormRepository.save(any()) }
        verify (exactly = 1) { eventProducer.send(any(), any()) }
    }

    @Test
    fun `모임 생성 실패 - feignClient 서버 에러`() {

        //given
        val memberId = 1L
        val applyFormFieldNames = listOf("이름", "나이", "성별")
        val createMeetingDto = MeetingFixture.createMeetingDto()
        val createApplyFormDto = ApplyFormFixture.createApplyFormDto(applyFormFields = applyFormFieldNames)

        every { memberServiceClient.getMember(memberId) } throws FeignClientException(ExceptionResponse(code = 0, message = "서버 에러 발생!"))

        //when, then
        expectThrows<FeignClientException> {  createMeetingUseCase.command(memberId, createMeetingDto, createApplyFormDto) }
    }

    @Test
    fun `모임 생성 실패 - 생성하려는 사람이 블랙리스티인 경우`() {

        //given
        val memberId = 1L
        val applyFormFieldNames = listOf("이름", "나이", "성별")
        val createMeetingDto = MeetingFixture.createMeetingDto()
        val createApplyFormDto = ApplyFormFixture.createApplyFormDto(applyFormFields = applyFormFieldNames)
        every { memberServiceClient.getMember(memberId) } returns FeignClientFixture.memberData(role = Role.BLACK)

        //when, then
        expectThrows<NotAuthorityCreateMeeting> {  createMeetingUseCase.command(memberId, createMeetingDto, createApplyFormDto) }
    }
}