package com.mohang.meeting.query.dao.meeting

import com.mohang.meeting.domain.meeting.QMeeting.meeting
import com.mohang.meeting.domain.participant.QParticipant.participant
import com.mohang.meeting.query.data.meeting.MeetingData
import com.mohang.meeting.query.data.meeting.QMeetingData
import com.mohang.meeting.query.exception.NotFoundMeetingException
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/11.
 */
@Repository
@Transactional(readOnly = true)
class MeetingDataDaoQuery(

    private val query: JPAQueryFactory,
) : MeetingDataDao {

    override fun findById(id: Long): MeetingData {
        return query.select(
            QMeetingData(
                meeting.id,
                meeting.name,
                meeting.description,
                meeting.capacity,
                meeting.createdAt.stringValue(),
                meeting.modifiedAt.stringValue(),

                participant.id,
                participant.memberId,
                participant.nickname
            )
        )
            .from(meeting, participant)
            .where(meeting.id.eq(id))
            .fetchOne()
            ?: throw NotFoundMeetingException()
    }
}