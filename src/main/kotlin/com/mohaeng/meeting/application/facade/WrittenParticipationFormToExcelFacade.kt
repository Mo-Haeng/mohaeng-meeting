package com.mohaeng.meeting.application.facade

import com.mohaeng.meeting.application.usecase.writtenparticipationform.WrittenParticipationFormToExcelUseCase
import com.mohaeng.meeting.query.dao.participationform.ParticipationFormDataDao
import com.mohaeng.meeting.query.dao.writtenparticipationform.WrittenParticipationFormDataDao
import org.springframework.stereotype.Service
import java.io.OutputStream

/**
 * 작성된 가입 신청서를 엑셀로 변환합니다.
 *
 * Created by ShinD on 2022/09/16.
 */
@Service
class WrittenParticipationFormToExcelFacade(

    private val writtenParticipationFormDataDao: WrittenParticipationFormDataDao,
    private val participationFormDataDao: ParticipationFormDataDao,
    private val writtenParticipationFormToExcelUseCase: WrittenParticipationFormToExcelUseCase,

    ) {


    fun createExcel(
        applyFormId: Long,
        os: OutputStream,
    ) {

        // 가입 신청서 양식 조회
        val participationFormData = participationFormDataDao.findById(applyFormId)

        // 작성된 가입 신청서 양식 조회
        val writtenParticipationFormDatas = writtenParticipationFormDataDao.getAllByParticipationFormId(applyFormId)

        writtenParticipationFormToExcelUseCase.command(
            os,
            participationFormData,
            writtenParticipationFormDatas,
        )
    }
}