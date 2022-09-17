package com.mohaeng.meeting.query.dao.writtenparticipationform

import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormData
import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormFieldData
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/16.
 */
@Service
class MockWrittenParticipationFormDataDao : WrittenParticipationFormDataDao {

    override fun getAllByParticipationFormId(meetingId: Long): List<WrittenParticipationFormData> {
        //TODO 고치기!!
        val writtenParticipationFormData = WrittenParticipationFormData(1L, 1L, "2022-01-02", "2022-02-02")
        val writtenField1 = WrittenParticipationFormFieldData(1L, 11L, "1번 내용")
        val writtenField2 = WrittenParticipationFormFieldData(2L, 22L, "2번 내용")
        val writtenField3 = WrittenParticipationFormFieldData(3L, 33L, "3번 내용")
        val writtenField4 = WrittenParticipationFormFieldData(4L, 44L, "4번 내용")

        writtenParticipationFormData.addAllFields(
            listOf(writtenField1, writtenField2, writtenField3, writtenField4)
        )

        val writtenParticipationFormData2 = WrittenParticipationFormData(2L, 1L, "2022-11-22", "2022-12-02")
        val writtenField2_1 = WrittenParticipationFormFieldData(5L, 11L, "1번 내용 두번째인간")
        val writtenField2_2 = WrittenParticipationFormFieldData(6L, 22L, "2번 내용 두번째인간")
        val writtenField2_3 = WrittenParticipationFormFieldData(7L, 33L, "3번 내용 두번째인간")
        val writtenField2_4 = WrittenParticipationFormFieldData(8L, 44L, "4번 내용 두번째인간")

        writtenParticipationFormData2.addAllFields(
            listOf(writtenField2_1, writtenField2_2, writtenField2_3, writtenField2_4)
        )

        return listOf(writtenParticipationFormData, writtenParticipationFormData2)
    }
}