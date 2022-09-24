package com.mohaeng.meeting.domain.meeting.query.dao

import com.mohaeng.meeting.domain.meeting.query.data.MeetingData

/**
 * Created by ShinD on 2022/09/11.
 */
interface MeetingDataDao {

    fun findById(meetingId: Long): MeetingData
}