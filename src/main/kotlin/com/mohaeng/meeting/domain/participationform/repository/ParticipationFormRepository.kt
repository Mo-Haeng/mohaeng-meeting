package com.mohaeng.meeting.domain.participationform.repository

import com.mohaeng.meeting.domain.participationform.domain.ParticipationForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/08.
 */
interface ParticipationFormRepository : JpaRepository<ParticipationForm, Long> {
}