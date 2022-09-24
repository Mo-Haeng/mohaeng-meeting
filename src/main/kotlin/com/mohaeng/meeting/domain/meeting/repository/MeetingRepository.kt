package com.mohaeng.meeting.domain.meeting.repository

import com.mohaeng.meeting.domain.meeting.domain.Meeting
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface MeetingRepository : JpaRepository<Meeting, Long> {
}