package com.mohang.meeting.query.dao.meeting

import com.mohang.meeting.query.data.meeting.MeetingData

/**
 * Created by ShinD on 2022/09/11.
 */
interface MeetingDataDao {

    fun findById(id: Long): MeetingData
}