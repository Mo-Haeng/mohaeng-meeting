package com.mohang.meeting.presentation

import com.mohang.meeting.query.dao.meeting.MeetingDataDao
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
    @GetMapping("/api/meeting/{meetingId}")
    fun inquireMeeting(@PathVariable(name = "meetingId") meetingId: Long) =
        ResponseEntity.ok(meetingDataDao.findById(meetingId))
}