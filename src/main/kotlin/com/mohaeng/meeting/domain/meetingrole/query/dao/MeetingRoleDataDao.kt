package com.mohaeng.meeting.domain.meetingrole.query.dao

import com.mohaeng.meeting.domain.meetingrole.query.data.MeetingRoleData

/**
 * Created by ShinD on 2022/09/11.
 */
interface MeetingRoleDataDao {

    // 모임의 기본 역할의 id 반환
    fun findDefaultRoleIdByMeetingId(meetingId: Long): Long

    // 특정 모임의 모든 역할 조회하기
    fun findAllRolesByMeetingId(meetingId: Long): List<MeetingRoleData>

    /**
     * 미팅 ID와 회원의 ID를 통해
     * 해당 미팅에서의 회원의 역할 조회
     */
    fun findByMemberIdAndMeetingId(memberId: Long, meetingId: Long): MeetingRoleData
}