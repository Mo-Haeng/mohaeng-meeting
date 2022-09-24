package com.mohaeng.meeting.domain.participant.query.dao

import com.mohaeng.meeting.domain.participant.query.data.ParticipantData

/**
 * Created by ShinD on 2022/09/13.
 */
interface ParticipantDataDao {

    fun existByMemberIdAndMeetingId (
        memberId: Long,
        meetingId: Long,
    ): Boolean

    fun findByParticipantId (
        participantId: Long,
    ): ParticipantData

    fun findByMeetingId (
        meetingId: Long
    ): List<ParticipantData>
}