package com.mohaeng.meeting.domain.writtenparticipationform.facade

import com.mohaeng.meeting.domain.participant.exception.NoAuthorityAcceptParticipationRequestException
import com.mohaeng.meeting.domain.writtenparticipationform.exception.NoExistParticipationFormException
import com.mohaeng.meeting.domain.writtenparticipationform.usecase.WrittenParticipationFormToExcelUseCase
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.domain.meetingrole.query.dao.MeetingRoleDataDao
import com.mohaeng.meeting.domain.participationform.query.dao.ParticipationFormDataDao
import com.mohaeng.meeting.domain.writtenparticipationform.query.dao.WrittenParticipationFormDataDao
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

    private val meetingRoleDataDao: MeetingRoleDataDao,

    ) {

    /**
     * 작성된 가입 신청서를 엑셀로 변환한다
     *
     * @return 엑셀 파일 이름(= 가입 신청서 양식의 이름)
     */
    @Log
    fun createExcel(
        participantFormId: Long,
        memberId: Long,
        os: OutputStream,
    ): String {

        // 가입 신청서 양식 조회 (가입 신청서 양식이 없는 경우 예외 발생)
        val participationForm =
            participationFormDataDao.findById(participantFormId)
            ?: throw NoExistParticipationFormException()

        checkNotNull(participationForm.meetingId)
        { "발생하면 안되는 오류 - 가입 신청서 양식에 모임 id가 반드시 포함되어야 합니다." }

        // 엑셀 다운로드를 요청한 사람은 관리자 이상의 권한이 있어야 함
        checkRequesterAuthority(
            memberId = memberId,
            meetingId = participationForm.meetingId,
        )

        // 작성된 가입 신청서 양식 조회
        val writtenParticipationFormDatas =
            writtenParticipationFormDataDao.getAllByParticipationFormId(participantFormId)

        // 엑셀로 작성
        writtenParticipationFormToExcelUseCase.command(
            os = os,
            participationFormData = participationForm,
            writtenParticipationFormDatas = writtenParticipationFormDatas,
        )

        // 가입 신청서 양식의 이름 반환
        return participationForm.name!!
    }


    /**
     * 요청자의 권한 확인
     */
    private fun checkRequesterAuthority(
        memberId: Long,
        meetingId: Long,
    ) {
        val meetingRole = meetingRoleDataDao.findByMemberIdAndMeetingId(
            memberId = memberId,
            meetingId = meetingId,
        )

        if (!meetingRole.authority.isMoreThanManager()) throw NoAuthorityAcceptParticipationRequestException()
    }
}