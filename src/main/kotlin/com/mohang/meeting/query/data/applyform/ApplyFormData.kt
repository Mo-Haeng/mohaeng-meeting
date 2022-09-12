package com.mohang.meeting.query.data.applyform

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class ApplyFormData @QueryProjection constructor(

    val id: Long, // 가입 신청서 양식의 id

    val createdAt: String, // 가입 신청서 생성일
    val modifiedAt: String, // 가입 신청서 수정일

    val name: String, // 가입 신청서 양식의 이름

    val meetingId: Long, // 해당 가입 신청서 양식을 모임 ID

) {

    val fields: MutableList<ApplyFormFieldData> = mutableListOf() //

    //필드 모두 추가
    fun addAllFields(fields: List<ApplyFormFieldData>) {
        this.fields.addAll(fields)
    }
}

data class ApplyFormFieldData @QueryProjection constructor(

    val id: Long,

    val name: String, // 필드 이름
)