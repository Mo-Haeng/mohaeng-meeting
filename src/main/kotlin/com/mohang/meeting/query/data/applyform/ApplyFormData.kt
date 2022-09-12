package com.mohang.meeting.query.data.applyform

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/11.
 */
data class ApplyFormData (

    val isExist: Boolean = false, // 가입 신청서가 존재하지 않을수도 있으므로

    val id: Long? = null, // 가입 신청서 양식의 id

    val createdAt: String? = null, // 가입 신청서 생성일

    val modifiedAt: String? = null, // 가입 신청서 수정일

    val name: String? = null, // 가입 신청서 양식의 이름

    val meetingId: Long? = null, // 해당 가입 신청서 양식을 모임 ID

) {
    @QueryProjection constructor(
        id: Long, // 가입 신청서 양식의 id

        createdAt: String, // 가입 신청서 생성일

        modifiedAt: String, // 가입 신청서 수정일

        name: String, // 가입 신청서 양식의 이름

        meetingId: Long, // 해당 가입 신청서 양식을 모임 ID

    ) : this(true, id, createdAt, modifiedAt, name, meetingId = meetingId)


    val fields: MutableList<ApplyFormFieldData> = mutableListOf() // 가입 신청서 필드명 리스트



    //필드 모두 추가
    fun addAllFields(fields: List<ApplyFormFieldData>) {
        this.fields.addAll(fields)
    }
}

data class ApplyFormFieldData @QueryProjection constructor(

    val id: Long,

    val name: String, // 필드 이름
)