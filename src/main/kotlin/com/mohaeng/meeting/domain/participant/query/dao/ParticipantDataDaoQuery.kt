package com.mohaeng.meeting.domain.participant.query.dao

import com.mohaeng.meeting.domain.meetingrole.domain.QMeetingRole
import com.mohaeng.meeting.domain.meetingrole.domain.QMeetingRole.meetingRole
import com.mohaeng.meeting.domain.meetingrole.exception.NotFoundParticipant
import com.mohaeng.meeting.domain.meetingrole.query.data.QMeetingRoleData
import com.mohaeng.meeting.domain.participant.domain.QParticipant
import com.mohaeng.meeting.domain.participant.domain.QParticipant.participant
import com.mohaeng.meeting.domain.participant.query.data.ParticipantData
import com.mohaeng.meeting.domain.participant.query.data.QParticipantData
import com.mohaeng.meeting.global.aop.log.Log
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

    @Log
    override fun findByParticipantId(participantId: Long): ParticipantData {

        return query.select(
            QParticipantData(
                participant.id,
                participant.memberId,
                participant.nickname,
                participant.profileImagePath,
                participant.meetingId,

                meetingRole.id,
                meetingRole.name,
                meetingRole.authority
            )
        )
            .from(participant)
            .innerJoin(meetingRole)
            .on(participant.meetingRoleId.eq(meetingRole.id))
            .where(
                participant.id.eq(participantId)
            ).fetchOne()
            ?: throw NotFoundParticipant()
    }


    @Log
    override fun findByMeetingId(meetingId: Long): List<ParticipantData> {
        return query.select(
            QParticipantData(
                participant.id,
                participant.memberId,
                participant.nickname,
                participant.profileImagePath,
                participant.meetingId,

                meetingRole.id,
                meetingRole.name,
                meetingRole.authority
            )
        )
            .from(participant)
            .innerJoin(meetingRole)
            .on(
                participant.meetingRoleId.eq(meetingRole.id)
            )
            .where(
                participant.meetingId.eq(meetingId)
            ).fetch()
            ?: throw NotFoundParticipant()
    }

}