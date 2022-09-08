package com.mohang.meeting.domain.applyform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "APPLY_FORM_FIELD")
class ApplyFormField(

    private val name: String, // 필드 이름

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "apply_form_id", nullable = false)
    private val applyForm: ApplyForm? = null, // 가입 신청서 양식
) : BaseEntity(){
}