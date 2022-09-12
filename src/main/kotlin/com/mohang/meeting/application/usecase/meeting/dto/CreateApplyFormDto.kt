package com.mohang.meeting.application.usecase.meeting.dto

import com.mohang.meeting.domain.applyform.ApplyForm
import com.mohang.meeting.domain.applyform.ApplyFormField

/**
 * Created by ShinD on 2022/09/08.
 */
data class CreateApplyFormDto(

    val applyFormName: String,

    val applyFormFields: List<ApplyFormFieldDto>
) {

    fun toEntity(meetingId: Long): ApplyForm {
        // applyForm 생성
        val applyForm = ApplyForm(name = applyFormName, isCurrentUsed = true, meetingId = meetingId)

        // 필드 모두 추가
        applyForm.addAllFields(applyFormFields.map { it.toEntity() })

        return applyForm
    }
}

data class ApplyFormFieldDto(
    val name: String
){
    fun toEntity() =
        ApplyFormField(name = name)
}
