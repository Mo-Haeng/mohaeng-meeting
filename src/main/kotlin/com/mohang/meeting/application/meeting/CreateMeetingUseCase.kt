package com.mohang.meeting.application.meeting

import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import com.mohang.meeting.domain.member.MemberServiceClient
import com.mohang.meeting.infrastructure.eventproducer.EventProducer
import com.mohang.meeting.infrastructure.persistence.applyform.ApplyFormRepository
import com.mohang.meeting.infrastructure.persistence.meeting.MeetingRepository
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import com.mohang.meeting.infrastructure.persistence.participant.ParticipantRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/07.
 */
@Service
class CreateMeetingUseCase(

    private val meetingRepository: MeetingRepository,

) {

    fun command(createMeetingDto: CreateMeetingDto) =
        meetingRepository.save(createMeetingDto.toEntity()).id!!
}