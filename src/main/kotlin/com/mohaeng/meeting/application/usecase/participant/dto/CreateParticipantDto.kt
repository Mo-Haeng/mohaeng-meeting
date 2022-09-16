package com.mohaeng.meeting.application.usecase.participant.dto

/**
 * Created by ShinD on 2022/09/12.
 */
data class CreateParticipantDto(

    // 회원 ID
    val memberId: Long,

    // 모임에서의 별명
    val nickname: String,

    // 모임에서 사용할 프사 url
    val profileImagePath: String? = null

) {
}