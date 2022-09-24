package com.mohaeng.meeting.domain.writtenparticipationform.repository

import com.mohaeng.meeting.domain.writtenparticipationform.domain.WrittenParticipationForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface WrittenParticipationFormRepository : JpaRepository<WrittenParticipationForm, Long> {
}