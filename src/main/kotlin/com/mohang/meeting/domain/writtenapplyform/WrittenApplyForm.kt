package com.mohang.meeting.domain.writtenapplyform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "WRITTEN_APPLY_FORM")
class WrittenApplyForm(

    private val memberId: Long, // 해당 양식을 작성한 회원 ID (* 참여자 ID가 아닌 회원 ID임에 주의 *)

    private val applyFormId: Long, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지

    @OneToMany(mappedBy = "writtenApplyForm", orphanRemoval = true, cascade = [ALL])
    private val writtenApplyFormFields: MutableList<WrittenApplyFormField> = mutableListOf(),

    ) : BaseEntity() {

    fun addAll(writtenApplyFormFields: List<WrittenApplyFormField>) {
       this.writtenApplyFormFields.addAll(writtenApplyFormFields)
    }
}