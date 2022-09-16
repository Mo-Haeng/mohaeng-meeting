package com.mohaeng.meeting.application.usecase.participant

import com.mohaeng.meeting.application.usecase.participant.dto.CreateParticipantDto
import com.mohaeng.meeting.domain.participant.Participant
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.infrastructure.persistence.participant.ParticipantRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantUseCase(

    private val participantRepository: ParticipantRepository,

    ) {

    @Log
    fun command(
        createParticipantDto: CreateParticipantDto,
        meetingId: Long,
        meetingRoleId: Long,
    ): Long {

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
}