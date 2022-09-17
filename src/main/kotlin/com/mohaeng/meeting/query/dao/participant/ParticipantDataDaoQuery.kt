package com.mohaeng.meeting.query.dao.participant

import com.mohaeng.meeting.domain.participant.QParticipant.participant
import com.mohaeng.meeting.infrastructure.log.Log
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/13.
 */
@Repository
@Transactional(readOnly = true)
class ParticipantDataDaoQuery(

    private val query: JPAQueryFactory,

    ) : ParticipantDataDao {

    @Log
    override fun existByMemberIdAndMeetingId(
        memberId: Long,
        meetingId: Long,
    ): Boolean {

        return query.selectOne()
            .from(participant)
            .where(
                participant.memberId.eq(memberId)
                    .and(participant.meetingId.eq(meetingId))
            )
            .fetchFirst() != null // 1개가 있는지 없는지 판단 (없으면 null이라 null체크)
    }
}