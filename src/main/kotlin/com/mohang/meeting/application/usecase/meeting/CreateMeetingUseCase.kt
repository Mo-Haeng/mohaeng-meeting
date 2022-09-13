package com.mohang.meeting.application.usecase.meeting

import com.mohang.meeting.application.usecase.meeting.dto.CreateMeetingDto
import com.mohang.meeting.infrastructure.log.Log
import com.mohang.meeting.infrastructure.persistence.meeting.MeetingRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/07.
 */
@Service
class CreateMeetingUseCase(

    private val meetingRepository: MeetingRepository,

    ) {

    @Log
    fun command(createMeetingDto: CreateMeetingDto) =
        meetingRepository.save(createMeetingDto.toEntity()).id!!
}