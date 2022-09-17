package com.mohaeng.meeting.application.usecase.participant.dto

import com.mohaeng.meeting.domain.writtenparticipationform.WrittenParticipationForm
import com.mohaeng.meeting.domain.writtenparticipationform.WrittenParticipationFormField

/**
 * Created by ShinD on 2022/09/12.
 */
data class SaveWrittenParticipationFormDto(

    val memberId: Long, // 해당 양식을 작성한 회원 ID (* 참여자 ID가 아닌 회원 ID임에 주의 *)

    val participationFormId: Long, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지

    val writtenParticipationFormFields: List<WrittenParticipationFormDto> = listOf(),

    ) {

    fun toEntity(): WrittenParticipationForm {

        val writtenParticipationForm = WrittenParticipationForm(
            memberId = memberId,
            participationId = participationFormId,
        )

        writtenParticipationForm.addAll(writtenParticipationFormFields.map { it.toEntity(writtenParticipationForm) })

        return writtenParticipationForm
    }
}

data class WrittenParticipationFormDto(

    val participationFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    val content: String, // 작성된 내용
) {

    fun toEntity(writtenParticipationForm: WrittenParticipationForm): WrittenParticipationFormField {
        return WrittenParticipationFormField(
            participationFormFieldId = participationFormFieldId,
            content = content,
            writtenParticipationForm = writtenParticipationForm,
        )
    }
}