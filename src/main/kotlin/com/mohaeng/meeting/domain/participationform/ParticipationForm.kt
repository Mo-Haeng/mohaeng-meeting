package com.mohaeng.meeting.domain.participationform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import com.mohaeng.meeting.infrastructure.log.Log
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "PARTICIPATION_FORM")
class ParticipationForm(

    @Column(nullable = false)
    val meetingId: Long, // 어떤 모임에서 정해진 규칙인지

    val name: String, // 신청 양식 이름

    val isCurrentUsed: Boolean = true, // 현재 사용중인지 여부, 단 하나만 현재 사용중일 수 있으므로, 유일한 true값을 가진다.

    @OneToMany(mappedBy = "participationForm", orphanRemoval = true, cascade = [ALL])
    val participationFormFields: MutableList<ParticipationFormField> = mutableListOf(), // 가입 신청 시 작성해야 할 필드 양식들

) : BaseEntity() {

    @Log
    fun addAllFields(participationFormFields: List<ParticipationFormField>) {
        participationFormFields.forEach { it.confirmApplyForm(this) }
        this.participationFormFields.addAll(participationFormFields)
    }
}