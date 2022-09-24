package com.mohaeng.meeting.domain.meeting.presentation

import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.domain.meeting.query.dao.MeetingDataDao
import com.mohaeng.meeting.domain.meeting.query.data.MeetingData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ShinD on 2022/09/11.
 */
@RestController
class InquireMeetingRestController(

    private val meetingDataDao: MeetingDataDao,
) {

    /**
     * 모임 정보 조회
     */
    @Log
    @GetMapping("/api/meeting/{meetingId}")
    fun inquireMeeting(
        @PathVariable(name = "meetingId") meetingId: Long,
    ): ResponseEntity<MeetingData> =
        ResponseEntity.ok(meetingDataDao.findById(meetingId))
}