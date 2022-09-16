package com.mohaeng.meeting.application.facade

import com.mohaeng.meeting.application.usecase.applyform.CreateApplyFormUseCase
import com.mohaeng.meeting.application.usecase.meeting.CreateMeetingUseCase
import com.mohaeng.meeting.application.usecase.meeting.dto.CreateApplyFormDto
import com.mohaeng.meeting.application.usecase.meeting.dto.CreateMeetingDto
import com.mohaeng.meeting.application.usecase.meetingrole.CreateDefaultMeetingRoleUseCase
import com.mohaeng.meeting.application.usecase.participant.RegisterParticipantUseCase
import com.mohaeng.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohaeng.meeting.infrastructure.eventproducer.EventProducer
import com.mohaeng.meeting.infrastructure.log.Log
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

    private val createApplyFormUseCase: CreateApplyFormUseCase,

    private val eventProducer: EventProducer,

    private val transaction: TransactionTemplate,
) {

    @Log
    fun create(
        createMeetingDto: CreateMeetingDto,
        createApplyFormDto: CreateApplyFormDto?,
        createParticipantDto: CreateParticipantDto,
    ): Long {


        val meetingId = transaction.execute {

            // 미팅 저장
            val meetingId = createMeetingUseCase.command(createMeetingDto)

            // 모임의 대표 역할과 일반 회원 역할 설정
            val representativeRoleId = createDefaultMeetingRoleUseCase
                .createAndReturnRepresentativeRoleId(meetingId)

            // 신청 회원을 모임 참가자(대표)로 설정
            registerParticipantUseCase.command(
                createParticipantDto = createParticipantDto,
                meetingId = meetingId,
                meetingRoleId = representativeRoleId
            )

            // 가입 신청서 양식이 존재하는 경우 저장
            createApplyFormDto?.let {
                createApplyFormUseCase.command(
                    createApplyFormDto = it,
                    meetingId = meetingId
                )
            }

            meetingId // return
        }


        checkNotNull(meetingId) { "meeting id is null" }

        // 이벤트 전송
        eventProducer.send(
            event = "create meeting event",
            targetId = meetingId
        )

        return meetingId
    }
}