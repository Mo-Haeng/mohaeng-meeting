package com.mohang.meeting.fixture

import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import com.mohang.meeting.domain.meeting.Meeting
import org.springframework.test.util.ReflectionTestUtils

/**
 * Created by ShinD on 2022/09/07.
 */
object MeetingFixture {

    private const val ID: Long = 1L

    private const val NAME: String = "meeting name" // 모임의 이름

    private const val DESCRIPTION: String = "meeting description" // 모임 설명

    private const val CAPACITY: Int = -1 // 모임 최대 인원 (-1은 제한 없음을 의미한다.)

    private const val APPLY_FORM_NAME = "가입 신청서 양식 이름"


    fun createMeetingDto(
        name: String = NAME,
        description: String = DESCRIPTION,
        capacity: Int = CAPACITY,

    ): CreateMeetingDto {
       return CreateMeetingDto(
           name = name,
           description = description,
           capacity = capacity,
       )
    }

    fun savedMeeting(
        id: Long = ID,
        name: String = NAME,
        description: String = DESCRIPTION,
        capacity: Int = CAPACITY,
    ): Meeting {

        val meeting = Meeting(
            name = name,
            description = description,
            capacity = capacity,
        )

        ReflectionTestUtils.setField(meeting, "id", id)
        return meeting
    }

    fun meeting(
        name: String = NAME,
        description: String = DESCRIPTION,
        capacity: Int = CAPACITY,
    ) =
        Meeting(
            name = name,
            description = description,
            capacity = capacity,
        )
}