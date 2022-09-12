package com.mohang.meeting.presentation.model

import com.mohang.meeting.application.participant.dto.CreateParticipantDto

/**
 * Created by ShinD on 2022/09/12.
 */
data class CreateParticipantRequest(

    // 모임에서의 별명
    val nickname: String,

    // 모임에서 사용할 프사 url
    val profileImagePath: String? = null

) {

    fun toServiceDto(memberId: Long): CreateParticipantDto =
        CreateParticipantDto(
            memberId = memberId,
            nickname = nickname,
            profileImagePath = profileImagePath,
        )
}