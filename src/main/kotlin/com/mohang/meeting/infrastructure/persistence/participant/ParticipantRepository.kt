package com.mohang.meeting.infrastructure.persistence.participant

import com.mohang.meeting.domain.participant.Participant
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface ParticipantRepository : JpaRepository<Participant, Long> {
}