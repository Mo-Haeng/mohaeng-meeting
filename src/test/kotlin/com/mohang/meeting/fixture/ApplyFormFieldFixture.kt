package com.mohang.meeting.fixture

import com.mohang.meeting.application.meeting.dto.ApplyFormFieldDto

/**
 * Created by ShinD on 2022/09/07.
 */
object ApplyFormFieldFixture {

    private const val ID: Long = 1L

    private const val NAME: String = "apply form field name"

    fun applyFormFieldDto(
        name: String = NAME,
    ) =
        ApplyFormFieldDto(
            name = name
        )
}