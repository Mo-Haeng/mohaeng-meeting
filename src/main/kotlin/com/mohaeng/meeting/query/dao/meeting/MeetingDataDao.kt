package com.mohaeng.meeting.query.dao.meeting

import com.mohaeng.meeting.query.data.meeting.MeetingData

/**
 * Created by ShinD on 2022/09/11.
 */
interface MeetingDataDao {

    fun findById(id: Long): MeetingData
}