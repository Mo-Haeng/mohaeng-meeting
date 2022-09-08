package com.mohang.meeting.application.meeting.dto

import com.mohang.meeting.domain.meeting.Meeting

/**
 * Created by ShinD on 2022/09/07.
 */
data class CreateMeetingDto(

    val name: String,

    val description: String,

    val capacity: Int = -1, //제한 없음 설정시 -1
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
