package com.mohaeng.meeting.domain.meeting.usecase.dto

import com.mohaeng.meeting.domain.meeting.domain.Meeting

/**
 * Created by ShinD on 2022/09/07.
 */
data class CreateMeetingDto(

    val name: String,

    val description: String,

    val mainImagePath: String? = null,

    val capacity: Int,
) {

    fun toEntity(): Meeting {
        // 모임 반환
        return Meeting(
            name = name,
            mainImagePath = mainImagePath,
            description = description,
            capacity = capacity,
        )
    }
}
