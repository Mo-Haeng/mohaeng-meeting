package com.mohang.meeting.query.data.meetingrole

import com.mohang.meeting.domain.meetingrole.enums.MeetingAuthority
import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class MeetingRoleData @QueryProjection constructor(

    val id: Long, // 역할의 id

    val name: String, // 역할의 이름

    val authority: MeetingAuthority, // 역할의 권한

    val meetingId: Long, // 해당 역할을 가진 meeting id

    val isParticipantDefault: Boolean, // 해당 역할이 신규 회원 참가 시 기본으로 부여되는 역할인지

) {
}