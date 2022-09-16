package com.mohaeng.meeting.infrastructure.persistence.writtenapplyform

import com.mohaeng.meeting.domain.writtenapplyform.WrittenApplyForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface WrittenApplyFormRepository : JpaRepository<WrittenApplyForm, Long> {
}