package com.mohaeng.meeting.application.facade

import com.mohaeng.meeting.application.facade.exception.NoAuthorityAcceptParticipationRequest
import com.mohaeng.meeting.application.usecase.participant.RegisterParticipantUseCase
import com.mohaeng.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohaeng.meeting.application.usecase.participant.dto.SaveWrittenParticipationFormDto
import com.mohaeng.meeting.application.usecase.writtenparticipationform.SaveWrittenParticipationFormUseCase
import com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.presentation.model.AuthMember
import com.mohaeng.meeting.query.dao.meetingrole.MeetingRoleDataDao
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantFacade(

    private val registerParticipantUseCase: RegisterParticipantUseCase,

    private val saveWrittenParticipationFormUseCase: SaveWrittenParticipationFormUseCase,

    private val meetingRoleDataDao: MeetingRoleDataDao,

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
        saveWrittenParticipationFormDto: SaveWrittenParticipationFormDto?, // 작성된 가입 신청서 저장시 필요
        meetingId: Long,
    ): Long {

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

            // 가입 신청서가 있다면 저장한다.
            saveWrittenParticipationFormUseCase.command(
                meetingId = meetingId,
                saveWrittenFormDto = saveWrittenParticipationFormDto
            )

            participantId // return
        }!!
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
}