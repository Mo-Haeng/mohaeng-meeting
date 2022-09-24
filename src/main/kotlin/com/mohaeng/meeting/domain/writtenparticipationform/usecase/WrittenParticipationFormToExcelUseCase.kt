package com.mohaeng.meeting.domain.writtenparticipationform.usecase

import com.mohaeng.meeting.query.data.participationform.ParticipationFormData
import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormData
import java.io.OutputStream

/**
 * 작성된 신청 양식을 엑셀로 변환할 때 사용한다.
 *
 * Created by ShinD on 2022/09/17.
 */
interface WrittenParticipationFormToExcelUseCase {

    fun command(
        os: OutputStream,
        participationFormData: ParticipationFormData,
        writtenParticipationFormDatas: List<WrittenParticipationFormData>,
    )
}