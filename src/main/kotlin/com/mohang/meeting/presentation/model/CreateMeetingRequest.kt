package com.mohang.meeting.presentation.model

import com.mohang.meeting.application.meeting.dto.ApplyFormFieldDto
import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto
import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import javax.validation.Valid
import javax.validation.constraints.NotBlank

/**
 * Created by ShinD on 2022/09/09.
 */
class CreateMeetingRequest(

    @field:NotBlank private val name: String,

    @field:NotBlank private val description: String,

    private val capacity: Int = -1, //제한 없음 설정시 -1

    @field:NotBlank private val applyFormName: String,

    private val applyFormFields: List<ApplyFormFieldRequest> = mutableListOf()
) {

    fun toServiceMeetingDto() =
        CreateMeetingDto(
            name = name,
            description = description,
            capacity = if (capacity <= 0) Int.MAX_VALUE else capacity,
        )


    fun toServiceApplyFormDto() =
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