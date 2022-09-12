package com.mohang.meeting.presentation

import com.mohang.meeting.query.dao.applyform.ApplyFormDataDao
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
    @GetMapping("/api/meeting/{meetingId}/applyform")
    fun inquireUsedApplyFormByMeetingId(@PathVariable(name = "meetingId") meetingId: Long) =
        ResponseEntity.ok(applyFormDataDao.findUsedApplyFormByMeetingId(meetingId))
}