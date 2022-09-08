package com.mohang.meeting.domain.applyform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.CascadeType.ALL
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "APPLY_FORM")
class ApplyForm(

    @Column(nullable = false)
    private val meetingId: Long, // 어떤 모임에서 정해진 규칙인지

    private val name: String, // 신청 양식 이름

    private val isCurrentUsed: Boolean = true, // 현재 사용중인지 여부, 단 하나만 현재 사용중일 수 있으므로, 유일한 true값을 가진다.

    @OneToMany(mappedBy = "applyForm", orphanRemoval = true, cascade = [ALL])
    private val applyFormFields: MutableList<ApplyFormField> = mutableListOf(),

    ) : BaseEntity() {

    fun addAllFields(applyFormFields: List<ApplyFormField>) {
       this.applyFormFields.addAll(applyFormFields)
    }
}