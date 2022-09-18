package com.mohaeng.meeting.application.usecase.meeting.dto

import com.mohaeng.meeting.domain.participationform.ParticipationForm
import com.mohaeng.meeting.domain.participationform.ParticipationFormField

/**
 * Created by ShinD on 2022/09/08.
 */
data class CreateParticipationFormDto(

    val participationFormName: String,

    val participationFormFields: List<ParticipationFormFieldDto>
) {

    fun toEntity(meetingId: Long): ParticipationForm {
        // applyForm 생성
        val participationForm = ParticipationForm(name = participationFormName, isCurrentUsed = true, meetingId = meetingId)

        // 필드 모두 추가
        participationForm.addAllFields(participationFormFields.map { it.toEntity() })

        return participationForm
    }
}

data class ParticipationFormFieldDto(
    val name: String
){
    fun toEntity() =
        ParticipationFormField(name = name)
}
