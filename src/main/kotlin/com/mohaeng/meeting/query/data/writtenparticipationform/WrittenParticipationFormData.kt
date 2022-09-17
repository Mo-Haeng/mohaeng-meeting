package com.mohaeng.meeting.query.data.writtenparticipationform

import com.querydsl.core.annotations.QueryProjection

/**
 * Created by ShinD on 2022/09/16.
 */
data class WrittenParticipationFormData
@QueryProjection constructor(

    val id: Long, // 작성한 가입 신청서 양식의 id

    val participationFormId: Long, // 가입 신청서 양식의 id

    val createdAt: String, // 가입 신청서 생성일

    val modifiedAt: String, // 가입 신청서 수정일
) {

    val fields: MutableList<WrittenParticipationFormFieldData> = mutableListOf() // 가입 신청서 필드명 리스트


    //필드 모두 추가
    fun addAllFields(fields: List<WrittenParticipationFormFieldData>) {
        this.fields.addAll(fields)
    }
}

data class WrittenParticipationFormFieldData
@QueryProjection constructor(
    val id: Long,

    val participationFormFieldId: Long,

    val content: String, // 작성된 내용
) {

}