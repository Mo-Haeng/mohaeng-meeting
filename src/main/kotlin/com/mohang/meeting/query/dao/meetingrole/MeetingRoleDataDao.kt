package com.mohang.meeting.query.dao.meetingrole

import com.mohang.meeting.query.data.meetingrole.MeetingRoleData

/**
 * Created by ShinD on 2022/09/11.
 */
interface MeetingRoleDataDao {

    // 모임의 기본 역할의 id 반환
    fun findDefaultRoleIdByMeetingId(meetingId: Long): Long

    // 특정 모임의 모든 역할 조회하기
    fun findAllRolesByMeetingId(meetingId: Long): List<MeetingRoleData>
}