package com.mohaeng.meeting.query.data.participant

import com.mohaeng.meeting.query.data.meetingrole.MeetingRoleData
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

    val meetingRoleData: MeetingRoleData, // 모임의 역할 정보

){
}