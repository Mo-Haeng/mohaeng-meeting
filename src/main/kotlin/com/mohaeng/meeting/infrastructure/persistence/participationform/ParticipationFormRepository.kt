package com.mohaeng.meeting.infrastructure.persistence.participationform

import com.mohaeng.meeting.domain.participationform.ParticipationForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/08.
 */
interface ParticipationFormRepository : JpaRepository<ParticipationForm, Long> {
}