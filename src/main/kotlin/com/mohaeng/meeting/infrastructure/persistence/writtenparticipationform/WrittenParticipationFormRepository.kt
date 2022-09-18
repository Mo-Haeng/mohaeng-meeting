package com.mohaeng.meeting.infrastructure.persistence.writtenparticipationform

import com.mohaeng.meeting.domain.writtenparticipationform.WrittenParticipationForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface WrittenParticipationFormRepository : JpaRepository<WrittenParticipationForm, Long> {
}