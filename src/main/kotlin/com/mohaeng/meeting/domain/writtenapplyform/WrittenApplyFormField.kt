package com.mohaeng.meeting.domain.writtenapplyform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "WRITTEN_APPLY_FORM_FIELD")
class WrittenApplyFormField(

    @Column(nullable = false)
    private val applyFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    private val content: String, // 작성된 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "written_apply_form_id")
    private val writtenApplyForm: WrittenApplyForm,

) : BaseEntity() {
}