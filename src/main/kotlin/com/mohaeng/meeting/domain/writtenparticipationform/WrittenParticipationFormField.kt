package com.mohaeng.meeting.domain.writtenparticipationform

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(name = "WRITTEN_PARTICIPATION_FORM_FIELD")
class WrittenParticipationFormField(

    @Column(nullable = false)
    private val participationFormFieldId: Long, // 어느 신청 양식 필드에 대한 작성값인지

    private val content: String, // 작성된 내용

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "written_participation_form_id")
    private val writtenParticipationForm: WrittenParticipationForm,

    ) : BaseEntity() {
}