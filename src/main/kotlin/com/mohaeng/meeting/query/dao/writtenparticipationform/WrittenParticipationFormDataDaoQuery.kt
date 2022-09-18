package com.mohaeng.meeting.query.dao.writtenparticipationform

import com.mohaeng.meeting.domain.writtenparticipationform.QWrittenParticipationForm.writtenParticipationForm
import com.mohaeng.meeting.domain.writtenparticipationform.QWrittenParticipationFormField.writtenParticipationFormField
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.data.writtenparticipationform.QWrittenParticipationFormData
import com.mohaeng.meeting.query.data.writtenparticipationform.QWrittenParticipationFormFieldData
import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormData
import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormFieldData
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by ShinD on 2022/09/16.
 */
@Repository
@Transactional(readOnly = true)
class WrittenParticipationFormDataDaoQuery(

    private val query: JPAQueryFactory,
) : WrittenParticipationFormDataDao {

    @Log
    override fun getAllByParticipationFormId(participationFormId: Long): List<WrittenParticipationFormData> {

        val writtenParticipationFormDatas = query.select(
            QWrittenParticipationFormData(
                writtenParticipationForm.id,
                writtenParticipationForm.participationFormId,
                writtenParticipationForm.createdAt.stringValue(),
                writtenParticipationForm.modifiedAt.stringValue(),
            )
        )
            .from(writtenParticipationForm)
            .where(
                writtenParticipationForm.participationFormId.eq(participationFormId)
            )
            .fetch()

        val writtenForms = writtenParticipationFormDatas.map { it.id }

        val writtenParticipationFormFieldDatas = query.select(
            QWrittenParticipationFormFieldData(
                writtenParticipationFormField.id,
                writtenParticipationFormField.writtenParticipationForm.id,
                writtenParticipationFormField.participationFormFieldId,
                writtenParticipationFormField.content,
            )
        )
            .from(writtenParticipationFormField)
            .where(
                writtenParticipationFormField.writtenParticipationForm.id.`in`(writtenForms)
            )
            .orderBy(writtenParticipationFormField.participationFormFieldId.asc()) // 오름차순으로 해주어야 필드와 작성내용이 알맞아짐
            .fetch()

        // 작성된 양식 id로 그룹화
        val writtenFieldMap: Map<Long, List<WrittenParticipationFormFieldData>> =
            writtenParticipationFormFieldDatas.groupBy { it.writtenParticipationFormId }

        writtenParticipationFormDatas.forEach {

            writtenForm ->

            writtenFieldMap[writtenForm.id]?. let { writtenField ->
                writtenForm.addAllFields(writtenField)
            }
        }
        return writtenParticipationFormDatas
    }
}