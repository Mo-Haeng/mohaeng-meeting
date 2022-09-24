package com.mohaeng.meeting.domain.meeting.query.data

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class MeetingData @QueryProjection constructor(

    val id: Long, // ID

    val name: String, // 모임의 이름

    val description: String, // 모임 설명

    val mainImagePath: String? = null,

    val capacity: Int, // 모임 최대 인원

    val createdAt: String, // 생성 시간

    val modifiedAt: String, // 최종 수정 시간

    val representativeParticipantId: Long, // 모임 대표의 참가자 ID(member id가 아님에 주의)

    val representativeMemberId: Long, // 대표의 회원 ID

    val representativeNickname: String, // 대표의 모임 내 별명

    var numberOfParticipants: Int, // 현재 가입한 회원 수
) {

}