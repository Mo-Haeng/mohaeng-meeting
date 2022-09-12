package com.mohang.meeting.application.usecase.writtenapplyform

import com.mohang.meeting.application.usecase.participant.dto.SaveWrittenApplyFormDto
import com.mohang.meeting.infrastructure.persistence.writtenapplyform.WrittenApplyFormRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/12.
 */
@Service
class SaveWrittenApplyFormUseCase(

    private val writtenApplyFormRepository: WrittenApplyFormRepository,

) {

    fun command(
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto
    ) {
       writtenApplyFormRepository.save(saveWrittenApplyFormDto.toEntity())
    }
}