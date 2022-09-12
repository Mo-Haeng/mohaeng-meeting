package com.mohang.meeting.application.meeting

import com.mohang.meeting.application.applyform.CreateApplyFormUseCase
import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto
import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import com.mohang.meeting.application.meeting.exception.NoAuthorityCreateMeeting
import com.mohang.meeting.application.meetingrole.CreateMeetingRoleUseCase
import com.mohang.meeting.application.member.TakeMemberDataUseCase
import com.mohang.meeting.application.participant.RegisterParticipantUseCase
import com.mohang.meeting.application.util.MemberUtil.Companion.ifBlacklistThrowException
import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.infrastructure.eventproducer.EventProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class CreateMeetingFacade(

    private val takeMemberDataUseCase: TakeMemberDataUseCase,

    private val createMeetingUseCase: CreateMeetingUseCase,

    private val createMeetingRoleUseCase: CreateMeetingRoleUseCase,

    private val registerParticipantUseCase: RegisterParticipantUseCase,

    private val createApplyFormUseCase: CreateApplyFormUseCase,

    private val eventProducer: EventProducer,

    private val transaction: TransactionTemplate,
) {

    fun create(
        memberId: Long,
        createMeetingDto: CreateMeetingDto,
        createApplyFormDto: CreateApplyFormDto
    ): Long {

        // Member Service로부터 회원 정보 조회
        val memberData = takeMemberDataUseCase.command(memberId)

        // 블랙리스트라면 예외 발생
        ifBlacklistThrowException(memberData, NoAuthorityCreateMeeting())

        val meetingId = transaction.execute {
            // 미팅 저장
            val meetingId = createMeetingUseCase.command(createMeetingDto)

            // 모임의 대표 역할과 일반 회원 역할 설정
            val representativeRoleId =
                saveDefaultRoleAndReturnRepresentativeRoleId(meetingId)

            // 신청 회원을 모임 참가자(대표)로 설정
            registerParticipantUseCase.command(memberData, meetingId, representativeRoleId)

            // 가입 신청서 양식 저장
            createApplyFormUseCase.command(createApplyFormDto, meetingId)

            meetingId // return
        }

        checkNotNull(meetingId) { "meeting id is null" }

        // 이벤트 전송
        eventProducer.send("create meeting event", meetingId)

        return meetingId
    }

    /**
     * 대표와 일반 회원 역할을 설정하고 저정함
     */
    private fun saveDefaultRoleAndReturnRepresentativeRoleId(meetingId: Long): Long {

        val representativeRole = MeetingRole.representativeRole(meetingId)
        val basicRole = MeetingRole.basicRole(meetingId)

        return createMeetingRoleUseCase.command(listOf(representativeRole, basicRole))[0].id!!
    }
}