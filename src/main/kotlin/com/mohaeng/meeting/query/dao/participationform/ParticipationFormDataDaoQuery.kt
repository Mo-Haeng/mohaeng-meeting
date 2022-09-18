package com.mohaeng.meeting.query.dao.participationform

import com.mohaeng.meeting.domain.participationform.QParticipationForm.participationForm
import com.mohaeng.meeting.domain.participationform.QParticipationFormField.participationFormField
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.data.participationform.ParticipationFormData
import com.mohaeng.meeting.query.data.participationform.ParticipationFormFieldData
import com.mohaeng.meeting.query.data.participationform.QParticipationFormData
import com.mohaeng.meeting.query.data.participationform.QParticipationFormFieldData
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/11.
 */
@Repository
@Transactional(readOnly = true)
class ParticipationFormDataDaoQuery(

    private val query: JPAQueryFactory,
) : ParticipationFormDataDao {

    @Log
    override fun findUsedParticipationFormByMeetingId(meetingId: Long): ParticipationFormData? {

        val participationFormData = query.select(
            QParticipationFormData(
                participationForm.id,
                participationForm.createdAt.stringValue(),
                participationForm.modifiedAt.stringValue(),
                participationForm.name,
                participationForm.meetingId,
            )
        )
            .from(participationForm)
            .where(
                participationForm.meetingId.eq(meetingId)
                    .and(participationForm.isCurrentUsed.isTrue)
            )
            .fetchOne()
            // 만약 가입 신청서가 없는 경우 곧바로 반환
            ?: return null

        val applyFormFieldDatas = query.select(
            QParticipationFormFieldData(
                participationFormField.id,
                participationFormField.name
            )
        )
            .from(participationFormField)
            .where(participationFormField.participationForm.id.eq(participationFormData.id))
            .fetch()

        participationFormData.addAllFields(applyFormFieldDatas)

        return participationFormData
    }

    @Log
    override fun findById(id: Long): ParticipationFormData? {

        val participationFormData = query.select(
            QParticipationFormData(
                participationForm.id,
                participationForm.createdAt.stringValue(),
                participationForm.modifiedAt.stringValue(),
                participationForm.name,
                participationForm.meetingId,
            )
        )
            .from(participationForm)
            .where(
                participationForm.id.eq(id)
            )
            .fetchOne()
        // 만약 가입 신청서가 없는 경우 존재하지 않음을 표시하는 데이터를 반환하고 종료
            ?: return null

        val applyFormFieldDatas = query.select(
            QParticipationFormFieldData(
                participationFormField.id,
                participationFormField.name
            )
        )
            .from(participationFormField)
            .where(participationFormField.participationForm.id.eq(participationFormData.id))

            // 기본이 오름차순이지만 확실히 하기 위해, 순서를 지정하지 않으면 엑셀 필드 매치가 되지 않아 오류가 발생할 수 있다.
            .orderBy(participationFormField.id.asc())
            .fetch()

        participationFormData.addAllFields(applyFormFieldDatas)

        return participationFormData
    }

}