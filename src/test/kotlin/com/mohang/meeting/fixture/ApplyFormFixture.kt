package com.mohang.meeting.fixture

import com.mohang.meeting.application.meeting.dto.ApplyFormFieldDto
import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto

/**
 * Created by ShinD on 2022/09/07.
 */
object ApplyFormFixture {

    private const val ID: Long = 1L

    private const val APPLY_FORM_NAME: String = "apply form name"

    private const val IS_CURRENT_USED: Boolean = true

    fun createApplyFormDto(
        applyFormName: String = APPLY_FORM_NAME,
        applyFormFields: List<String>,
    ): CreateApplyFormDto {
        return CreateApplyFormDto(
            applyFormName = applyFormName,
            applyFormFields = applyFormFields.map { ApplyFormFieldDto(name = it) },
        )
    }
}