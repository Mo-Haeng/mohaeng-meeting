package com.mohaeng.meeting.domain.meeting.facade

import com.mohaeng.meeting.domain.meeting.usecase.CreateMeetingUseCase
import com.mohaeng.meeting.domain.meeting.usecase.dto.CreateMeetingDto
import com.mohaeng.meeting.domain.meeting.usecase.dto.CreateParticipationFormDto
import com.mohaeng.meeting.domain.meetingrole.usecase.CreateDefaultMeetingRoleUseCase
import com.mohaeng.meeting.domain.participant.usecase.RegisterParticipantUseCase
import com.mohaeng.meeting.domain.participant.usecase.dto.CreateParticipantDto
import com.mohaeng.meeting.domain.participationform.usecase.CreateParticipationFormUseCase
import com.mohaeng.meeting.global.aop.event.ProduceEvent
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.global.event.Event.Companion.CREATE_MEETING_EVENT
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class CreateMeetingFacade(

    private val createMeetingUseCase: CreateMeetingUseCase,

    private val createDefaultMeetingRoleUseCase: CreateDefaultMeetingRoleUseCase,

    private val registerParticipantUseCase: RegisterParticipantUseCase,

    private val createParticipationFormUseCase: CreateParticipationFormUseCase,

    private val transaction: TransactionTemplate,
) {

    @Log
    @ProduceEvent(event = CREATE_MEETING_EVENT)
    fun create(
        createMeetingDto: CreateMeetingDto,
        createParticipationFormDto: CreateParticipationFormDto?,
        createParticipantDto: CreateParticipantDto,
    ): Long {

        val meetingId = transaction.execute {

            // 모임 생성
            val meetingId = createMeetingUseCase.command(createMeetingDto)

            // 모임의 대표 역할과 일반 회원 역할 설정
            val representativeRoleId =
                createDefaultMeetingRoleUseCase.createAndReturnRepresentativeRoleId(meetingId)

            // 신청 회원을 모임 참가자(대표)로 설정
            registerParticipantUseCase.command(
                createParticipantDto = createParticipantDto,
                meetingId = meetingId,
                meetingRoleId = representativeRoleId
            )

            // 가입 신청서 양식이 존재하는 경우 저장
            createParticipationFormUseCase.command(
                createFormDto = createParticipationFormDto,
                meetingId = meetingId
            )

            meetingId // return
        }

        checkNotNull(meetingId) { "meeting id is null" }

        return meetingId
    }
}