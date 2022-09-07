package com.mohang.meeting.infrastructure.persistence.writtenapplyform

import com.mohang.meeting.domain.writtenapplyform.WrittenApplyForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/07.
 */
interface WrittenApplyFormRepository : JpaRepository<WrittenApplyForm, Long> {
}