package com.mohang.meeting.presentation.model

import com.mohang.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohang.meeting.application.usecase.participant.dto.SaveWrittenApplyFormDto
import com.mohang.meeting.application.usecase.participant.dto.WrittenApplyFormDto

/**
 * Created by ShinD on 2022/09/13.
 */
data class RegisterParticipantRequest(

    // 회원 ID
    val memberId: Long,

    // 모임에서의 별명
    val nickname: String,

    // 모임에서 사용할 프사 url
    val profileImagePath: String? = null,

    val applyFormId: Long? = null, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지

    val writtenApplyFormFields: List<WrittenApplyFormRequest> = listOf(),

    ) {

    fun toServiceParticipantDto(): CreateParticipantDto {
        return CreateParticipantDto(
            memberId = memberId,
            nickname = nickname,
            profileImagePath = profileImagePath
        )
    }

    fun toServiceWrittenApplyFormDto(): SaveWrittenApplyFormDto? {

        return applyFormId?.let {

            val writtenApplyFormDtos = writtenApplyFormFields.map { it.toServiceDto() }

            SaveWrittenApplyFormDto(
                memberId = memberId,
                applyFormId = applyFormId,
                writtenApplyFormFields = writtenApplyFormDtos,
            )
        }
    }
}

data class WrittenApplyFormRequest(

    val applyFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    val content: String, // 작성된 내용
) {

    fun toServiceDto(): WrittenApplyFormDto {
        return WrittenApplyFormDto(
            applyFormFieldId = applyFormFieldId,
            content = content,
        )
    }
}