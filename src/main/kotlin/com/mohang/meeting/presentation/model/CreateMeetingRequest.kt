package com.mohang.meeting.presentation.model

import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto
import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import javax.validation.constraints.NotBlank

/**
 * Created by ShinD on 2022/09/09.
 */
class CreateMeetingRequest(

    // 모임의 이름
    @field:NotBlank private val name: String,

    // 모임의 설명
    @field:NotBlank private val description: String,

    // 모임 최대 인원
    private val capacity: Int = -1, //제한 없음 설정시 -1

    // 모임에 가입할 때 제출해야할 가입 신청서 양식 생성 (없는 경우 저장하지 않음)
    private val createApplyFormRequest: CreateApplyFormRequest? = null,

    // 모임에 가입할 프로필 (모임의 장의 프로필임)
    private val createParticipantRequest: CreateParticipantRequest

) {

    fun toServiceMeetingDto() =
        CreateMeetingDto(
            name = name,
            description = description,
            capacity = if (capacity <= 0) Int.MAX_VALUE else capacity,
        )

    fun toServiceApplyFormDto(): CreateApplyFormDto? =
       createApplyFormRequest?.toServiceDto()

    fun toServiceParticipantDto(memberId: Long) =
        createParticipantRequest.toServiceDto(memberId)
}