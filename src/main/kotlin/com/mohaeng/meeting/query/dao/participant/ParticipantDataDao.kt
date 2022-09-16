package com.mohaeng.meeting.query.dao.participant

/**
 * Created by ShinD on 2022/09/13.
 */
interface ParticipantDataDao {

    fun existByMemberIdAndMeetingId(
        memberId: Long,
        meetingId: Long,
    ): Boolean
}