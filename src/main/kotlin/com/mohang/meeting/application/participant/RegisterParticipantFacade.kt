package com.mohang.meeting.application.participant

import com.mohang.meeting.application.participant.dto.CreateParticipantDto
import com.mohang.meeting.application.participant.dto.SaveWrittenApplyFormDto
import com.mohang.meeting.application.participant.exception.NotMatchApplyFormException
import com.mohang.meeting.application.writtenapplyform.SaveWrittenApplyFormUseCase
import com.mohang.meeting.query.dao.applyform.ApplyFormDataDao
import com.mohang.meeting.query.dao.meetingrole.MeetingRoleDataDao
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantFacade(

    private val registerParticipantUseCase: RegisterParticipantUseCase,

    private val meetingRoleDataDao: MeetingRoleDataDao,

    private val applyFormDataDao: ApplyFormDataDao,

    private val saveWrittenApplyFormUseCase: SaveWrittenApplyFormUseCase,

    private val transaction: TransactionTemplate,
) {

    /**
     * meeting이 없다면 예외
     *
     * meeting의 기본 역할로 참가시키기
     */
    fun command(
        createParticipantDto: CreateParticipantDto,     // 참가하는 회원의 모임에서의 프로필 저장시 필요
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto?, // 작성된 가입 신청서 저장시 필요
        meetingId: Long,
    ): Long {

        // 모임의 기본 역할 조회하기 (모임이 존재하지 않거나, 모임의 기본 역할이 없는 경우 예외 (후자는 절대 발생하면 안되는 케이스))
        val defaultRoleId = meetingRoleDataDao.findDefaultRoleIdByMeetingId(meetingId)

        // 트랜잭션 시작
        return transaction.execute {

            // 회원 저장하기
            val participantId = registerParticipantUseCase.command(
                createParticipantDto = createParticipantDto,
                meetingId = meetingId,
                meetingRoleId = defaultRoleId
            )

            // 작성된 가입 신청서 저장하기
            saveWrittenApplyForm(
                meetingId = meetingId,
                saveWrittenApplyFormDto = saveWrittenApplyFormDto
            )

            participantId // return
        }!!
    }


    /**
     * 모임에서 현재 사용중인 가입 신청서를 조회한다.
     * 만약 현재 사용중인 가입신청서가 있는 경우 -> 작성된 가입 신청서의 양식과 비교하여 올바르다면 저장한다., 올바르지 않거나 없는 경우 예외를 발생시킨다. (작성해야 할 가입 신청서가 새로 생성되었거나 변경되었습니다.)
     */
    private fun saveWrittenApplyForm(
        meetingId: Long,
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto?,
    ) {
        val applyFormData = applyFormDataDao.findUsedApplyFormByMeetingId(meetingId)

        // 사용중인 가입 신청서 양식이 존재하지 않는 경우 저장을 시도하지 않고 반환
        if (!applyFormData.isExist) return

        // 작성된 가입 신청서가 없거나, 양식이 일치하지 않는다면 예외
        if (saveWrittenApplyFormDto == null || applyFormData.id == saveWrittenApplyFormDto.applyFormId) {
            throw NotMatchApplyFormException()
        }

        // 작성된 가입 신청서 저장
        saveWrittenApplyFormUseCase.command(saveWrittenApplyFormDto)
    }
}