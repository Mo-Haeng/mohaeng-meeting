package com.mohaeng.meeting.presentation.model

import com.mohaeng.meeting.domain.participant.usecase.dto.CreateParticipantDto
import com.mohaeng.meeting.domain.participant.usecase.dto.SaveWrittenParticipationFormDto
import com.mohaeng.meeting.domain.participant.usecase.dto.WrittenParticipationFormDto
import javax.validation.constraints.NotBlank

/**
 * Created by ShinD on 2022/09/13.
 */
data class RegisterParticipantRequest(

    // 회원 ID
    val memberId: Long,

    // 모임에서의 별명
    @field:NotBlank val nickname: String,

    // 모임에서 사용할 프사 url (없는 경우 "")
    val profileImagePath: String,

    val participationFormId: Long = -1, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지 (없는 경우 -1)

    val writtenParticipationFormFields: List<WrittenParticipationFormRequest> = listOf(),

    ) {

    fun toServiceParticipantDto(): CreateParticipantDto {
        return CreateParticipantDto(
            memberId = memberId,
            nickname = nickname,
            profileImagePath = profileImagePath.ifBlank { null }
        )
    }

    fun toServiceWrittenApplyFormDto(): SaveWrittenParticipationFormDto? {

        // 신청 양식의 id가 1 이상이면서, 필드가 모두 작성된 경우
        return if (participationFormId >= 1) {

            val writtenApplyFormDtos = writtenParticipationFormFields.map { it.toServiceDto() }

            SaveWrittenParticipationFormDto(
                memberId = memberId,
                participationFormId = participationFormId,
                writtenParticipationFormFields = writtenApplyFormDtos,
            )
        }
        else null
    }
}

data class WrittenParticipationFormRequest(

    @field:NotBlank val participationFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    @field:NotBlank val content: String, // 작성된 내용
) {

    fun toServiceDto(): WrittenParticipationFormDto {
        return WrittenParticipationFormDto(
            participationFormFieldId = participationFormFieldId,
            content = content,
        )
    }
}