package com.mohaeng.meeting.presentation.model

import com.mohaeng.meeting.application.usecase.meeting.dto.CreateMeetingDto
import com.mohaeng.meeting.application.usecase.meeting.dto.CreateParticipationFormDto
import com.mohaeng.meeting.application.usecase.meeting.dto.ParticipationFormFieldDto
import com.mohaeng.meeting.application.usecase.participant.dto.CreateParticipantDto
import javax.validation.constraints.NotBlank

/**
 * Created by ShinD on 2022/09/09.
 *
 * 모임 생성 시 필요한 데이터
 */
class CreateMeetingRequest(

    // 모임의 이름
    @field:NotBlank val name: String,

    // 모임의 설명
    @field:NotBlank val description: String,

    // 모임 최대 인원
    val capacity: Int = -1, //제한 없음 설정시 -1

    // 모임에 가입할 때 제출해야할 가입 신청서 양식 생성 (없는 경우 저장하지 않음)
    val createParticipationFormRequest: CreateParticipationFormRequest,

    // 모임에 가입할 프로필 (모임의 장의 프로필임)
    val createParticipantRequest: CreateParticipantRequest,

    ) {

    fun toServiceMeetingDto(): CreateMeetingDto =
        CreateMeetingDto(
            name = name,
            description = description,
            capacity = if (capacity <= 0) Int.MAX_VALUE else capacity,
        )

    fun toServiceApplyFormDto(): CreateParticipationFormDto? {
        // 만약 양식의 이름이 있다면 CreateApplyFormDto를 반환
        return createParticipationFormRequest.toServiceDto()
    }

    fun toServiceParticipantDto(memberId: Long): CreateParticipantDto =
        createParticipantRequest.toServiceDto(memberId)
}


/**
 *  모임에 가입할 때 제출해야할 가입 신청서 양식 (없는 경우 저장하지 않음)
 */
data class CreateParticipationFormRequest(

    // 모임의 가입 신청서 이름
    val participationFormName: String,

    // 모임 가입 시 작성해야 할 정보 이름들
    val participationFormFields: List<ParticipationFormFieldRequest> = mutableListOf(),
) {

    fun toServiceDto(): CreateParticipationFormDto? {

        // 양식의 이름이 빈칸이 아니면서, 필드가 1개 이상 존재하는 경우에만 생성
        return if (participationFormName.isNotBlank() && participationFormFields.isNotEmpty()) {
            CreateParticipationFormDto(
                participationFormName = participationFormName,
                participationFormFields = participationFormFields.map { it.toServiceDto() }
            )
        }
        else null
    }
}


/**
 *  모임에 가입할 때 제출해야할 가입 신청서 양식의 필드명들
 */
data class ParticipationFormFieldRequest(
    @field:NotBlank val name: String,
) {

    fun toServiceDto() = ParticipationFormFieldDto(name = name)
}

/**
 * 모임에서 사용할 프로필
 */
data class CreateParticipantRequest(

    // 모임에서의 별명
    val nickname: String,

    // 모임에서 사용할 프사 url
    val profileImagePath: String,

    ) {

    fun toServiceDto(memberId: Long): CreateParticipantDto =
        CreateParticipantDto(
            memberId = memberId,
            nickname = nickname,
            profileImagePath = profileImagePath.ifBlank { null },
        )
}