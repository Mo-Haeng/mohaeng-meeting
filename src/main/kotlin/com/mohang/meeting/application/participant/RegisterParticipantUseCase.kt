package com.mohang.meeting.application.participant

import com.mohang.meeting.domain.participant.Participant
import com.mohang.meeting.infrastructure.client.member.model.MemberData
import com.mohang.meeting.infrastructure.persistence.participant.ParticipantRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class RegisterParticipantUseCase(

    private val participantRepository: ParticipantRepository,

) {

    fun command(memberData: MemberData, meetingId: Long, meetingRoleId: Long) =
        with(memberData) {
            participantRepository.save(
                Participant(memberId = id, nickname = nickname, profileImagePath = profileImagePath, meetingId = meetingId, meetingRoleId = meetingRoleId)
            )
        }
}