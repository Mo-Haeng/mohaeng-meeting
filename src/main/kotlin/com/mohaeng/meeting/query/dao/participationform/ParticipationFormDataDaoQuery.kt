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
    override fun findUsedParticipationFormByMeetingId(meetingId: Long): ParticipationFormData {

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
        // 만약 가입 신청서가 없는 경우 존재하지 않음을 표시하는 데이터를 반환하고 종료
            ?: return ParticipationFormData(isExist = false)

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

    override fun findById(id: Long): ParticipationFormData {
        val participationFormData = ParticipationFormData(
            id = 1L,
            isExist = true,
            createdAt = "2011-01-01",
            modifiedAt = "2011-01-01",
            name = "이건 저희 동아리 양식 제목입니다",
            meetingId = 1L,
        )

        participationFormData.addAllFields(
            listOf(
                ParticipationFormFieldData(
                    id = 11L,
                    name = "1번 필드"
                ),

                ParticipationFormFieldData(
                    id = 22L,
                    name = "2번 필드"
                ),

                ParticipationFormFieldData(
                    id = 33L,
                    name = "3번 필드"
                ),

                ParticipationFormFieldData(
                    id = 44L,
                    name = "4번 필드"
                ),
            )
        )
        return participationFormData
        //TODO 이거 꼭 고쳐
    }

}