package com.mohang.meeting.domain.meeting

import com.mohang.meeting.configuration.jpa.BaseEntity

/**
 * Created by ShinD on 2022/09/07.
 */
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "APPLY_FORM_FIELD")
class ApplyFormField(

    private val name: String, // 필드 이름

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "apply_form_id", nullable = false)
    private val applyForm: ApplyForm, // 가입 신청서 양식
) : BaseEntity(){
}