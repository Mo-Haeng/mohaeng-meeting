package com.mohaeng.meeting.domain.meetingrole.query.dao

import com.mohaeng.meeting.domain.meetingrole.domain.QMeetingRole.meetingRole
import com.mohaeng.meeting.domain.meetingrole.exception.NotFoundDefaultRole
import com.mohaeng.meeting.domain.meetingrole.exception.NotFoundParticipant
import com.mohaeng.meeting.domain.meetingrole.query.data.MeetingRoleData
import com.mohaeng.meeting.domain.meetingrole.query.data.QMeetingRoleData
import com.mohaeng.meeting.domain.participant.domain.QParticipant.participant
import com.mohaeng.meeting.global.aop.log.Log
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/11.
 */
@Repository
@Transactional(readOnly = true)
class MeetingRoleDataDaoQuery(

    private val query: JPAQueryFactory,

    ) : MeetingRoleDataDao {

    @Log
    override fun findDefaultRoleIdByMeetingId(meetingId: Long): Long {

        return query
            .select(meetingRole.id)
            .from(meetingRole)
            .where(
                meetingRole.meetingId.eq(meetingId)
                    .and(meetingRole.isParticipantDefault.isTrue)
            )
            .fetchOne()
            ?: throw NotFoundDefaultRole()
    }

    @Log
    override fun findAllRolesByMeetingId(meetingId: Long): List<MeetingRoleData> {
        TODO("Not yet implemented")
    }

    @Log
    override fun findByMemberIdAndMeetingId(memberId: Long, meetingId: Long): MeetingRoleData {

        return query.select(
            QMeetingRoleData(
                meetingRole.id,
                meetingRole.name,
                meetingRole.authority,
                meetingRole.meetingId,
                meetingRole.isParticipantDefault,
            )
        )
            .from(meetingRole)
            .join(participant)
            .on(
                participant.meetingId.eq(meetingId)
                    .and(participant.memberId.eq(memberId))
            )
            .where(
                participant.meetingRoleId.eq(meetingRole.id)
            )
            .fetchOne()
            ?: throw NotFoundParticipant()
    }
}