package com.mohang.meeting.application.facade

import com.mohang.meeting.application.facade.exception.AlreadyExistParticipantException
import com.mohang.meeting.application.facade.exception.NoAuthorityAcceptParticipationRequest
import com.mohang.meeting.application.facade.exception.NotMatchApplyFormException
import com.mohang.meeting.application.facade.exception.UnfilledApplyFormFieldException
import com.mohang.meeting.application.usecase.participant.RegisterParticipantUseCase
import com.mohang.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohang.meeting.application.usecase.participant.dto.SaveWrittenApplyFormDto
import com.mohang.meeting.application.usecase.writtenapplyform.SaveWrittenApplyFormUseCase
import com.mohang.meeting.domain.meetingrole.enums.MeetingAuthority
import com.mohang.meeting.infrastructure.log.Log
import com.mohang.meeting.presentation.model.AuthMember
import com.mohang.meeting.query.dao.applyform.ApplyFormDataDao
import com.mohang.meeting.query.dao.meetingrole.MeetingRoleDataDao
import com.mohang.meeting.query.dao.participant.ParticipantDataDao
import com.mohang.meeting.query.data.applyform.ApplyFormData
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantFacade(

    private val registerParticipantUseCase: RegisterParticipantUseCase,

    private val saveWrittenApplyFormUseCase: SaveWrittenApplyFormUseCase,
    private val meetingRoleDataDao: MeetingRoleDataDao,

    private val applyFormDataDao: ApplyFormDataDao,

    private val participantDataDao: ParticipantDataDao,

    private val transaction: TransactionTemplate,
) {

    /**
     * meeting이 없다면 예외
     *
     * 승인자의 권한이 매니저 이상이 아니라면 예외
     *
     * meeting의 기본 역할로 참가시키기
     */
    @Log
    fun command(
        accepter: AuthMember,
        createParticipantDto: CreateParticipantDto,     // 참가하는 회원의 모임에서의 프로필 저장시 필요
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto?, // 작성된 가입 신청서 저장시 필요
        meetingId: Long,
    ): Long {

        // 이미 가입되어 있는지 확인
        checkDuplicateParticipation(
            memberId = createParticipantDto.memberId,
            meetingId = meetingId,
        )

        // 참가시키는 사람이 모임의 관리자 권한 이상을 가져야 함
        checkAccepterAuthority(
            accepter = accepter,
            meetingId = meetingId,
        )

        // 모임의 기본 역할 조회하기 (모임이 존재하지 않거나, 모임의 기본 역할이 없는 경우 예외 (후자는 절대 발생하면 안되는 케이스))
        val defaultRoleId = meetingRoleDataDao.findDefaultRoleIdByMeetingId(meetingId)

        // 트랜잭션 시작
        return transaction.execute {

            // 회원 저장하기
            val participantId = registerParticipantUseCase.command(
                createParticipantDto = createParticipantDto,
                meetingId = meetingId,
                meetingRoleId = defaultRoleId,
            )

            // 작성해야 할 가입 신청서와, 요청으로 보낸 가입 신청서 검사
            validateWrittenApplyForm(
                meetingId = meetingId,
                saveWrittenApplyFormDto = saveWrittenApplyFormDto,
            )

            // 가입 신청서가 있으며, 모든 필드가 채워진 경우 저장
            saveWrittenApplyFormDto?.let {
                saveWrittenApplyForm(saveWrittenApplyFormDto = it)
            }

            participantId // return
        }!!
    }

    /**
     * 중복 가입된 회원의 신청인지 확인한다.
     */
    private fun checkDuplicateParticipation(
        memberId: Long,
        meetingId: Long,
    ) {
        if (
            participantDataDao.existByMemberIdAndMeetingId(
                memberId = memberId,
                meetingId = meetingId
            )
        ) throw AlreadyExistParticipantException()
    }


    /**
     * 가입 승인을 한 사람은 관리자 권한 이상이어야 함.
     */
    private fun checkAccepterAuthority(
        accepter: AuthMember,
        meetingId: Long,
    ) {
        val meetingRole = meetingRoleDataDao.findByMemberIdAndMeetingId(
            memberId = accepter.id,
            meetingId = meetingId
        )

        when (meetingRole.authority) {
            // 매니저 이상의 등급이면 권한이 있음
            MeetingAuthority.REPRESENTATIVE -> return
            MeetingAuthority.MANAGER -> return
            MeetingAuthority.BASIC -> throw NoAuthorityAcceptParticipationRequest()
        }
    }


    /**
     * 모임에서 현재 사용중인 가입 신청서를 조회한다.
     * 만약 현재 사용중인 가입신청서가 있는 경우 -> 작성된 가입 신청서의 양식과 비교하여 올바르다면 반환, 올바르지 않거나 없는 경우 예외를 발생시킨다. (작성해야 할 가입 신청서가 새로 생성되었거나 변경되었습니다.)
     */
    private fun validateWrittenApplyForm(
        meetingId: Long,
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto?,
    ) {
        val applyFormData = applyFormDataDao.findUsedApplyFormByMeetingId(meetingId)

        // 사용중인 가입 신청서 양식이 존재하지 않는 경우 저장을 시도하지 않고 반환
        if (!applyFormData.isExist) return

        // 작성된 가입 신청서가 없거나, 양식이 일치하지 않는다면 예외
        if (saveWrittenApplyFormDto == null
            || applyFormData.id != saveWrittenApplyFormDto.applyFormId
        ) {
            throw NotMatchApplyFormException()
        }

        // 작성해야할 모든 필드를 작성한 건지 검사
        validateFields(saveWrittenApplyFormDto, applyFormData)
    }


    /**
     * 작성해야할 모든 필드를 작성한 건지 검사한다
     */
    private fun validateFields(
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto,
        applyFormData: ApplyFormData,
    ) {

        // 사이즈가 다른 경우 예외를 발생시킨다.
        if (saveWrittenApplyFormDto.writtenApplyFormFields.size != applyFormData.fields.size) {
            throw UnfilledApplyFormFieldException()
        }

        // 사이즈가 같은 경우 필요한 필드를 모두 작성했는지 검사한다.
        val requestApplyFormFieldIds = saveWrittenApplyFormDto.writtenApplyFormFields
            .map { it.applyFormFieldId }

        val applyFormFieldIds = applyFormData.fields
            .map { it.id }

        // id 가 모두 일치하지 않는다면 오류
        if (!requestApplyFormFieldIds.containsAll(applyFormFieldIds)) {
            throw UnfilledApplyFormFieldException()
        }
    }

    /**
     * 작성된 가입 신청서를 저장한다.
     */
    private fun saveWrittenApplyForm(
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto,
    ) {
        // 작성된 가입 신청서 저장
        saveWrittenApplyFormUseCase.command(saveWrittenApplyFormDto)
    }
}