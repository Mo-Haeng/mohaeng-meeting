package com.mohaeng.meeting.domain.meeting.query.dao

import com.mohaeng.meeting.domain.meeting.domain.QMeeting.meeting
import com.mohaeng.meeting.domain.meeting.exception.NotFoundMeetingException
import com.mohaeng.meeting.domain.meeting.query.data.MeetingData
import com.mohaeng.meeting.domain.meeting.query.data.QMeetingData
import com.mohaeng.meeting.domain.meetingrole.domain.QMeetingRole.meetingRole
import com.mohaeng.meeting.domain.meetingrole.domain.enums.MeetingAuthority.REPRESENTATIVE
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
class MeetingDataDaoQuery(

    private val query: JPAQueryFactory,
) : MeetingDataDao {

    @Log
    override fun findById(meetingId: Long): MeetingData {

        // 미팅의 대표 역할 ID 찾기 (없는 경우 모임이 없는 것과 동일)
        val meetingRepresentativeId = findMeetingRepresentativeRoleId(meetingId)

        val meetingData =
            query.select(
                QMeetingData(
                    meeting.id,
                    meeting.name,
                    meeting.description,
                    meeting.capacity,
                    meeting.createdAt.stringValue(),
                    meeting.modifiedAt.stringValue(),
                    participant.id, // 대표 참가자 id
                    participant.memberId,
                    participant.nickname
                )
            )
                .from(meeting)
                .innerJoin(participant)
                .on( // join 조건 필터링 (from절 세타조인 사용하면 cross join 발생함)
                    participant.meetingId.eq(meetingId)
                        .and(participant.meetingRoleId.eq(meetingRepresentativeId))
                )
                .where(meeting.id.eq(meetingId))
                .fetchOne()
                ?: throw NotFoundMeetingException()


        // 참여중인 회원 수 조회
        val numberOfParticipants = getParticipationCount(meetingId)

        meetingData.numberOfParticipants = numberOfParticipants

        return meetingData
    }

    /**
     * 참여중인 회원 수 조회
     */
    private fun getParticipationCount(meetingId: Long): Int {

        val numberOfParticipants = query
            .select(participant.count())
            .from(participant)
            .where(participant.meetingId.eq(meetingId))
            .fetchOne()
            ?: 0
        return numberOfParticipants.toInt()
    }

    /**
     * 모임의 대표 역할 id 조회
     */
    private fun findMeetingRepresentativeRoleId(id: Long): Long {
        return query.select(meetingRole.id)
            .from(meetingRole)
            .where(
                meetingRole.authority.eq(REPRESENTATIVE)
                    .and(meetingRole.meetingId.eq(id))
            )
            .fetchOne()
            ?: throw NotFoundMeetingException()
    }
}
