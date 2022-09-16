package com.mohaeng.meeting.application.usecase.meeting

import com.mohaeng.meeting.application.usecase.meeting.dto.CreateMeetingDto
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.infrastructure.persistence.meeting.MeetingRepository
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