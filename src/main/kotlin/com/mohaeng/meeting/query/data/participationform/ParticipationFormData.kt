package com.mohaeng.meeting.query.data.participationform

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class ParticipationFormData @QueryProjection constructor(

    val id: Long? = null, // 가입 신청서 양식의 id

    val createdAt: String? = null, // 가입 신청서 생성일

    val modifiedAt: String? = null, // 가입 신청서 수정일

    val name: String? = null, // 가입 신청서 양식의 이름

    val meetingId: Long? = null, // 해당 가입 신청서 양식을 모임 ID

) {

    val fields: MutableList<ParticipationFormFieldData> = mutableListOf() // 가입 신청서 필드명 리스트

    
    //필드 모두 추가
    fun addAllFields(fields: List<ParticipationFormFieldData>) {
        this.fields.addAll(fields)
    }
}

data class ParticipationFormFieldData @QueryProjection constructor(

    val id: Long,

    val name: String, // 필드 이름
)