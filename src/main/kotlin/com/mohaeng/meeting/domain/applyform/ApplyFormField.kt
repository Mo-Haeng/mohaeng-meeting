package com.mohaeng.meeting.domain.applyform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import com.mohaeng.meeting.infrastructure.log.Log
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "APPLY_FORM_FIELD")
class ApplyFormField(

    val name: String, // 필드 이름

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "apply_form_id", nullable = false)
    var applyForm: ApplyForm? = null, // 가입 신청서 양식

) : BaseEntity() {

    @Log
    fun confirmApplyForm(applyForm: ApplyForm) {
        this.applyForm = applyForm
    }
}