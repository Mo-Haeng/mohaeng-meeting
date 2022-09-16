package com.mohaeng.meeting.presentation

import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.query.dao.applyform.ApplyFormDataDao
import com.mohaeng.meeting.query.data.applyform.ApplyFormData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ShinD on 2022/09/11.
 */
@RestController
class InquireApplyFormRestController(

    private val applyFormDataDao: ApplyFormDataDao,
) {

    /**
     * 모임 가입 신청서 조회(현재 사용중인 신청서 단일 조회)
     */
    @Log
    @GetMapping("/api/meeting/{meetingId}/applyform")
    fun inquireUsedApplyFormByMeetingId(
        @PathVariable(name = "meetingId") meetingId: Long,
    ): ResponseEntity<ApplyFormData> =
        ResponseEntity.ok(applyFormDataDao.findUsedApplyFormByMeetingId(meetingId))
}