package com.mohaeng.meeting.infrastructure.excel

import com.mohaeng.meeting.application.usecase.writtenparticipationform.WrittenParticipationFormToExcelUseCase
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.data.participationform.ParticipationFormData
import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormData
import org.dhatim.fastexcel.Workbook
import org.springframework.stereotype.Service
import java.io.OutputStream

/**
 * Created by ShinD on 2022/09/17.
 */
@Service
class WrittenParticipationFormToExcelUseCaseImpl : WrittenParticipationFormToExcelUseCase {

    @Log
    override fun command(
        os: OutputStream,
        participationFormData: ParticipationFormData,
        writtenParticipationFormDatas: List<WrittenParticipationFormData>,
    ) {

        // 엑셀 파일 생성
        val workbook = Workbook(os, "MoHaeng", "1.0")

        // 엑셀 시트 생성
        val worksheet = workbook.newWorksheet(participationFormData.name!!)

        // 엑셀 최상단 row 작성
        participationFormData.fields.withIndex().forEach {
            worksheet.value(0, it.index, it.value.name)
        }

        // 작성된 내용 엑셀에 한줄씩 변환
        var row = 1 // row 번호
        for (writtenApplyForm in writtenParticipationFormDatas.withIndex()) {

            // 작성된 양식의 모든 필드 내용 작성
            writtenApplyForm.value.fields.withIndex().forEach {
                worksheet.value(row, it.index, it.value.content)
            }
            row++
        }

        // 마무리
        worksheet.flush()
        worksheet.finish()
        workbook.finish()
    }
}