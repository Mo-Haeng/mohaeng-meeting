package com.mohaeng.meeting.application.usecase.writtenapplyform

import com.mohaeng.meeting.application.usecase.participant.dto.SaveWrittenApplyFormDto
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.infrastructure.persistence.writtenapplyform.WrittenApplyFormRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/12.
 */
@Service
class SaveWrittenApplyFormUseCase(

    private val writtenApplyFormRepository: WrittenApplyFormRepository,

    ) {

    @Log
    fun command(
        saveWrittenApplyFormDto: SaveWrittenApplyFormDto,
    ) {
        writtenApplyFormRepository.save(saveWrittenApplyFormDto.toEntity())
    }
}