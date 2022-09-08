package com.mohang.meeting.application.meetingrole

import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class CreateMeetingRoleUseCase(

    private val meetingRoleRepository: MeetingRoleRepository,

) {

    fun command(meetingRoles: List<MeetingRole>): List<MeetingRole> =
        meetingRoleRepository.saveAll(meetingRoles)
}