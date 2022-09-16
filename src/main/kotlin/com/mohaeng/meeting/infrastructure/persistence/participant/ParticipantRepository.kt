package com.mohaeng.meeting.infrastructure.persistence.participant

import com.mohaeng.meeting.domain.participant.Participant
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface ParticipantRepository : JpaRepository<Participant, Long> {
}