package com.mohang.meeting.infrastructure.persistence.meeting

import com.mohang.meeting.domain.meeting.Meeting
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface MeetingRepository : JpaRepository<Meeting, Long> {
}