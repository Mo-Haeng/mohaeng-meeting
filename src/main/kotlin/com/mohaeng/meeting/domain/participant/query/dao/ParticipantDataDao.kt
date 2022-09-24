package com.mohaeng.meeting.domain.participant.query.dao

/**
 * Created by ShinD on 2022/09/13.
 */
interface ParticipantDataDao {

    fun existByMemberIdAndMeetingId(
        memberId: Long,
        meetingId: Long,
    ): Boolean
}