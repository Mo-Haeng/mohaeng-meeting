package com.mohaeng.meeting.domain.participationform.usecase

import com.mohaeng.meeting.domain.meeting.usecase.dto.CreateParticipationFormDto
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.domain.participationform.repository.ParticipationFormRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class CreateParticipationFormUseCase(

    private val participationFormRepository: ParticipationFormRepository,

    ) {

    /**
     * 가입 신청서 양식을 저장한다.
     * 가입 신청서 양식이 없는 경우 저장하지 않는다.
     *
     * @return 저장된 가입 신청서 양식의 id, 없는 경우 -1을 반환한다.
     */
    @Log
    fun command(
        createFormDto: CreateParticipationFormDto?,
        meetingId: Long,
    ): Long {

        // 가입 신청서가 없는 경우 바로 반환
        if (createFormDto == null) return -1L

        val applyForm = createFormDto.toEntity(meetingId)

        checkNotNull(applyForm.meetingId) { "applyForm의 meeting id가 설정되지 않았습니다." }

        return participationFormRepository.save(applyForm).id!!
    }
}