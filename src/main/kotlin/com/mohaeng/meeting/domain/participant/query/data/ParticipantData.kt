package com.mohaeng.meeting.domain.participant.query.data

import com.mohaeng.meeting.domain.meetingrole.domain.enums.MeetingAuthority
import com.mohaeng.meeting.domain.meetingrole.query.data.MeetingRoleData
import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/13.
 */
class ParticipantData @QueryProjection constructor(

    val id: Long, // 참가자 ID

    val memberId: Long, // 회원 ID

    val nickname: String, // 모임에서의 별명

    val profileImagePath: String? = null, // 모임에서의 프사 url

    val meetingId: Long, // 모임 ID

    val roleId: Long, // 역할의 id

    val roleName: String, // 역할의 이름

    val roleAuthority: MeetingAuthority, // 역할의 권한

){


}