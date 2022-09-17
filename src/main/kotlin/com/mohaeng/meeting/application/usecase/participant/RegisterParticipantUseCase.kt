package com.mohaeng.meeting.application.usecase.participant

import com.mohaeng.meeting.application.facade.exception.AlreadyExistParticipantException
import com.mohaeng.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohaeng.meeting.domain.participant.Participant
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.infrastructure.persistence.participant.ParticipantRepository
import com.mohaeng.meeting.query.dao.participant.ParticipantDataDao
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantUseCase(

    private val participantRepository: ParticipantRepository,

    private val participantDataDao: ParticipantDataDao,

    ) {

    /**
     * 회원을 모임에 참가시킵니다.
     * 만약 이미 가입되어 있다면 예외를 발생시킵니다.
     *
     * @return 생성된 모임의 id
     * @exception AlreadyExistParticipantException - 이미 모임에 참가한 회원의 경우 발생
     */
    @Log
    fun command(
        createParticipantDto: CreateParticipantDto,
        meetingId: Long,
        meetingRoleId: Long,
    ): Long {

        // 이미 가입되어 있는지 확인
        checkDuplicateParticipation(
            memberId = createParticipantDto.memberId,
            meetingId = meetingId,
        )

        // 참가자 Entity 생성
        val participant = Participant(
            memberId = createParticipantDto.memberId, // 회원 ID
            nickname = createParticipantDto.nickname, // 모임에서 사용할 별명
            profileImagePath = createParticipantDto.profileImagePath, // 모임에서 사용할 프사 url
            meetingId = meetingId, // 모임 ID
            meetingRoleId = meetingRoleId, // 모임에서 담당할 역할에 대한 ID
        )

        return participantRepository.save(participant).id!!
    }


    /**
     * 중복 가입된 회원의 신청인지 확인한다.
     */
    private fun checkDuplicateParticipation(
        memberId: Long,
        meetingId: Long,
    ) {
        if (participantDataDao.existByMemberIdAndMeetingId(
                memberId = memberId,
                meetingId = meetingId
            )
        )
            throw AlreadyExistParticipantException()
    }
}