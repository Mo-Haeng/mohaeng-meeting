package com.mohang.meeting.presentation.model

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

    // 모임에 가입할 때 제출해야할 가입 신청서 양식 생성
    private val createApplyFormRequest: CreateApplyFormRequest,

    // 모임에 가입할 프로필 (모임의 장의 프로필임)
    private val createParticipantRequest: CreateParticipantRequest

) {

    fun toServiceMeetingDto() =
        CreateMeetingDto(
            name = name,
            description = description,
            capacity = if (capacity <= 0) Int.MAX_VALUE else capacity,
        )

    fun toServiceApplyFormDto() =
       createApplyFormRequest.toServiceDto()

    fun toServiceParticipantDto() =
        createParticipantRequest.toServiceDto()
}