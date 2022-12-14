package com.mohaeng.meeting.domain.participationform.presentation

import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.domain.participationform.query.dao.ParticipationFormDataDao
import com.mohaeng.meeting.query.data.participationform.ParticipationFormData
import com.mohaeng.meeting.domain.participationform.exception.NotFoundParticipationFormException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * 가입 신청서 양식을 조회한다.
 *
 * Created by ShinD on 2022/09/11.
 */
@RestController
class InquireParticipationFormRestController(

    private val participationFormDataDao: ParticipationFormDataDao,
) {

    /**
     * 모임 가입 신청서 조회(현재 사용중인 신청서 단일 조회)
     */
    @Log
    @GetMapping("/api/meeting/{meetingId}/participation-form")
    fun inquireUsedApplyFormByMeetingId(
        @PathVariable(name = "meetingId") meetingId: Long,
    ): ResponseEntity<ParticipationFormData> {

        return ResponseEntity.ok(
            participationFormDataDao.findUsedParticipationFormByMeetingId(meetingId)
                ?: throw NotFoundParticipationFormException()
        )
    }
}