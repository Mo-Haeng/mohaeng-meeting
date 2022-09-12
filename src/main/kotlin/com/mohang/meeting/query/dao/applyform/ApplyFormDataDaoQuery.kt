package com.mohang.meeting.query.dao.applyform

import com.mohang.meeting.domain.applyform.QApplyForm.applyForm
import com.mohang.meeting.domain.applyform.QApplyFormField.applyFormField
import com.mohang.meeting.query.data.applyform.ApplyFormData
import com.mohang.meeting.query.data.applyform.QApplyFormData
import com.mohang.meeting.query.data.applyform.QApplyFormFieldData
import com.mohang.meeting.query.exception.NotFoundApplyFormException
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/11.
 */
@Repository
@Transactional(readOnly = true)
class ApplyFormDataDaoQuery(

    private val query: JPAQueryFactory,
) : ApplyFormDataDao {

    override fun findUsedApplyFormByMeetingId(meetingId: Long): ApplyFormData {

        val applyFormData = query.select(
            QApplyFormData(
                applyForm.id,
                applyForm.createdAt.stringValue(),
                applyForm.modifiedAt.stringValue(),
                applyForm.name,
                applyForm.meetingId,
            )
        )
            .from(applyForm)
            .where(
                applyForm.meetingId.eq(meetingId)
                    .and(applyForm.isCurrentUsed.isTrue)
            )
            .fetchOne()
            ?: throw NotFoundApplyFormException()

        val applyFormFieldDatas = query.select(
            QApplyFormFieldData(
                applyFormField.id,
                applyFormField.name
            )
        )
            .from(applyFormField)
            .where(applyFormField.applyForm.id.eq(meetingId))
            .fetch()

        applyFormData.addAllFields(applyFormFieldDatas)

        return applyFormData
    }

}