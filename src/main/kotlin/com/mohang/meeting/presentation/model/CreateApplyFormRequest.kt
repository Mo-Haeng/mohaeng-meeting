package com.mohang.meeting.presentation.model

import com.mohang.meeting.application.usecase.meeting.dto.ApplyFormFieldDto
import com.mohang.meeting.application.usecase.meeting.dto.CreateApplyFormDto
import javax.validation.constraints.NotBlank

/**
 * Created by ShinD on 2022/09/12.
 */
data class CreateApplyFormRequest(

    // 모임의 가입 신청서 이름
    @field:NotBlank private val applyFormName: String,

    // 모임 가입 시 작성해야 할 정보 이름들
    private val applyFormFields: List<ApplyFormFieldRequest> = mutableListOf()
) {
    fun toServiceDto() =
        CreateApplyFormDto(
            applyFormName = applyFormName,
            applyFormFields = applyFormFields.map { it.toServiceDto() }
        )
}

data class ApplyFormFieldRequest(
    @field:NotBlank val name: String
){
    fun toServiceDto() = ApplyFormFieldDto(name = name)
}