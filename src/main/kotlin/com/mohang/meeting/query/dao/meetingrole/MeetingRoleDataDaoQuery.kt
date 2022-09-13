package com.mohang.meeting.query.dao.meetingrole

import com.mohang.meeting.domain.meetingrole.QMeetingRole.meetingRole
import com.mohang.meeting.domain.participant.QParticipant
import com.mohang.meeting.domain.participant.QParticipant.participant
import com.mohang.meeting.query.data.meetingrole.MeetingRoleData
import com.mohang.meeting.query.data.meetingrole.QMeetingRoleData
import com.mohang.meeting.query.exception.NotFoundDefaultRole
import com.mohang.meeting.query.exception.NotFountParticipant
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
        println(memberId)
        println(meetingId)
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
            ?: throw NotFountParticipant()
    }
}