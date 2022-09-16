package com.mohaeng.meeting.infrastructure.persistence.applyform

import com.mohaeng.meeting.domain.applyform.ApplyForm
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by ShinD on 2022/09/08.
 */
interface ApplyFormRepository : JpaRepository<ApplyForm, Long>{
}