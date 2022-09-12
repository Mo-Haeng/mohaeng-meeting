package com.mohang.meeting.query.data.meeting

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class MeetingData @QueryProjection constructor(

    val id: Long, // ID

    val name: String, // 모임의 이름

    val description: String, // 모임 설명

    val capacity: Int, // 모임 최대 인원

    // 현재 가입한 회원 수는 모임 노출 서비스에서 구현

    val createdAt: String, // 생성 시간

    val modifiedAt: String, // 최종 수정 시간


    val representativeParticipantId: Long, // 모임 대표의 참가자 ID(member id가 아님에 주의)

    val representativeMemberId: Long, // 해당 참가자의 회원 ID

    val representativeNickname: String, // 모임에서 사용할 별명

) {
}