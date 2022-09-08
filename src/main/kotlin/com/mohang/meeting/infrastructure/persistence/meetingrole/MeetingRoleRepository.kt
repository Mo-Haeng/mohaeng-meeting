package com.mohang.meeting.infrastructure.persistence.meetingrole

import com.mohang.meeting.domain.meetingrole.MeetingRole
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface MeetingRoleRepository : JpaRepository<MeetingRole, Long> {
}