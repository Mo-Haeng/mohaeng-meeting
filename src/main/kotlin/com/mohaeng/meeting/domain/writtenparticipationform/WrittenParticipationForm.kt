package com.mohaeng.meeting.domain.writtenparticipationform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import com.mohaeng.meeting.infrastructure.log.Log
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "WRITTEN_PARTICIPATION_FORM")
class WrittenParticipationForm(

    private val memberId: Long, // 해당 양식을 작성한 회원 ID (* 참여자 ID가 아닌 회원 ID임에 주의 *)

    private val participationFormId: Long, // 해당 작성된 신청서는 어느 신청양식을 작성한 것인지

    @OneToMany(mappedBy = "writtenParticipationForm", orphanRemoval = true, cascade = [ALL])
    private val writtenParticipationFormFields: MutableList<WrittenParticipationFormField> = mutableListOf(),

    ) : BaseEntity() {

    @Log
    fun addAll(writtenParticipationFormFields: List<WrittenParticipationFormField>) {
        this.writtenParticipationFormFields.addAll(writtenParticipationFormFields)
    }
}