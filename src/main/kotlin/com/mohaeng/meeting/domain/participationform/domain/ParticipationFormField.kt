package com.mohaeng.meeting.domain.participationform.domain

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.global.configuration.jpa.BaseEntity
import com.mohaeng.meeting.global.aop.log.Log
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "PARTICIPATION_FORM_FIELD")
class ParticipationFormField(

    val name: String, // 필드 이름

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "participation_form_id", nullable = false)
    var participationForm: ParticipationForm? = null, // 가입 신청서 양식

) : BaseEntity() {

    @Log
    fun confirmApplyForm(participationForm: ParticipationForm) {
        this.participationForm = participationForm
    }
}