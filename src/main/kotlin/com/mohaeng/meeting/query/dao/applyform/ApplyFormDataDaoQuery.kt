package com.mohaeng.meeting.query.dao.applyform

import com.mohaeng.meeting.domain.applyform.QApplyForm.applyForm
import com.mohaeng.meeting.domain.applyform.QApplyFormField.applyFormField
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.data.applyform.ApplyFormData
import com.mohaeng.meeting.query.data.applyform.QApplyFormData
import com.mohaeng.meeting.query.data.applyform.QApplyFormFieldData
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

    @Log
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
            // 만약 가입 신청서가 없는 경우 존재하지 않음을 표시하는 데이터를 반환하고 종료
            ?: return ApplyFormData(isExist = false)

        val applyFormFieldDatas = query.select(
            QApplyFormFieldData(
                applyFormField.id,
                applyFormField.name
            )
        )
            .from(applyFormField)
            .where(applyFormField.applyForm.id.eq(applyFormData.id))
            .fetch()

        applyFormData.addAllFields(applyFormFieldDatas)

        return applyFormData
    }

}