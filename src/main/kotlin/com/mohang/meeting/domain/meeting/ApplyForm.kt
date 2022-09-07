package com.mohang.meeting.domain.meeting

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "APPLY_FORM")
class ApplyForm(

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "meeting_id")
    private val meeting: Meeting,

    private val isCurrentUsed: Boolean, // 현재 사용중인지 여부, 단 하나만 현재 사용중일 수 있으므로, 유일한 true값을 가진다.

    @OneToMany(mappedBy = "applyForm", orphanRemoval = true, cascade = [ALL])
    private val applyFormFields: MutableList<ApplyFormField> = mutableListOf(),

) : BaseEntity() {
}