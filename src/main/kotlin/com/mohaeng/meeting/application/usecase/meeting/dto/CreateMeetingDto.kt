package com.mohaeng.meeting.application.usecase.meeting.dto

import com.mohaeng.meeting.domain.meeting.Meeting

/**
 * Created by ShinD on 2022/09/07.
 */
data class CreateMeetingDto(

    val name: String,

    val description: String,

    val capacity: Int,
) {

    fun toEntity(): Meeting {
        // 모임 반환
        return Meeting(
            name = name,
            description = description,
            capacity = capacity,
        )
    }
}
