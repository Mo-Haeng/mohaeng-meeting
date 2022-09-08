package com.mohang.meeting.application.applyform

import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto
import com.mohang.meeting.infrastructure.persistence.applyform.ApplyFormRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class CreateApplyFormUseCase(

    private val applyFormRepository: ApplyFormRepository,

) {

    fun command(createApplyFormDto: CreateApplyFormDto, meetingId: Long): Long  {

        val applyForm = createApplyFormDto.toEntity(meetingId)

        checkNotNull(applyForm.meetingId) { "applyForm의 meeting id가 설정되지 않았습니다." }

        return applyFormRepository.save(applyForm).id!!
    }
}