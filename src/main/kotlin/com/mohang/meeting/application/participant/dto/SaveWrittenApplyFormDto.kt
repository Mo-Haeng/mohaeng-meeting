package com.mohang.meeting.application.participant.dto

import com.mohang.meeting.domain.writtenapplyform.WrittenApplyForm
import com.mohang.meeting.domain.writtenapplyform.WrittenApplyFormField

/**
 * Created by ShinD on 2022/09/12.
 */
data class SaveWrittenApplyFormDto(

    val memberId: Long, // 해당 양식을 작성한 회원 ID (* 참여자 ID가 아닌 회원 ID임에 주의 *)

    val applyFormId: Long, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지

    val writtenApplyFormFields: MutableList<WrittenApplyFormDto> = mutableListOf(),

    ) {

    fun toEntity(): WrittenApplyForm {

        val writtenApplyForm = WrittenApplyForm(
            memberId = memberId,
            applyFormId = applyFormId,
        )

        writtenApplyForm.addAll(writtenApplyFormFields.map { it.toEntity(writtenApplyForm) })

        return writtenApplyForm
    }
}

data class WrittenApplyFormDto(

    val applyFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    val content: String, // 작성된 내용
) {

    fun toEntity(writtenApplyForm: WrittenApplyForm): WrittenApplyFormField {
        return WrittenApplyFormField(
            applyFormFieldId = applyFormFieldId,
            content = content,
            writtenApplyForm = writtenApplyForm,
        )
    }
}