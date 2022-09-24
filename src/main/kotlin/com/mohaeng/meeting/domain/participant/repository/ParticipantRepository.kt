package com.mohaeng.meeting.domain.participant.repository

import com.mohaeng.meeting.domain.participant.domain.Participant
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface ParticipantRepository : JpaRepository<Participant, Long> {
}