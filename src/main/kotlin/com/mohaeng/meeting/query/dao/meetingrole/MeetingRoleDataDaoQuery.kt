package com.mohaeng.meeting.query.dao.meetingrole

import com.mohaeng.meeting.domain.meetingrole.QMeetingRole.meetingRole
import com.mohaeng.meeting.domain.participant.QParticipant.participant
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.data.meetingrole.MeetingRoleData
import com.mohaeng.meeting.query.data.meetingrole.QMeetingRoleData
import com.mohaeng.meeting.query.exception.NotFoundDefaultRole
import com.mohaeng.meeting.query.exception.NotFoundParticipant
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

    override fun findAllRolesByMeetingId(meetingId: Long): List<MeetingRoleData> {
        TODO("Not yet implemented")
    }

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