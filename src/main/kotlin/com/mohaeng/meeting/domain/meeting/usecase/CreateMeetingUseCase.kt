package com.mohaeng.meeting.domain.meeting.usecase

import com.mohaeng.meeting.domain.meeting.usecase.dto.CreateMeetingDto
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.domain.meeting.repository.MeetingRepository
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