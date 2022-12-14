package com.mohaeng.meeting.domain.writtenparticipationform.query.dao

import com.mohaeng.meeting.domain.writtenparticipationform.domain.QWrittenParticipationForm.writtenParticipationForm
import com.mohaeng.meeting.domain.writtenparticipationform.domain.QWrittenParticipationFormField.writtenParticipationFormField
import com.mohaeng.meeting.global.aop.log.Log
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
            .orderBy(writtenParticipationFormField.participationFormFieldId.asc()) // ?????????????????? ???????????? ????????? ??????????????? ????????????
            .fetch()

        // ????????? ?????? id??? ?????????
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