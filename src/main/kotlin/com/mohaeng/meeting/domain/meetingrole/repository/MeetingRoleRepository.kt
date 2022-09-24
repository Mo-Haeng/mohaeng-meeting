package com.mohaeng.meeting.domain.meetingrole.repository

import com.mohaeng.meeting.domain.meetingrole.domain.MeetingRole
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface MeetingRoleRepository : JpaRepository<MeetingRole, Long> {
}