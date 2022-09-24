package com.mohaeng.meeting.domain.participant.presentation

import com.mohaeng.meeting.domain.participant.query.dao.ParticipantDataDao
import com.mohaeng.meeting.domain.participant.query.data.ParticipantData
import com.mohaeng.meeting.global.aop.log.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * 모임에 존재하는 회원들 조회
 *
 * Created by ShinD on 2022/09/25.
 */
@RestController
class InquireParticipantRestController(
    private val participantDataDao: ParticipantDataDao,
) {

    @Log
    @GetMapping("/api/participation/meeting/{meetingId}")
    fun inquireParticipantList(@PathVariable meetingId: Long): List<ParticipantData> {
        return participantDataDao.findByMeetingId(meetingId)
    }

    @Log
    @GetMapping("/api/participation/{participationId}")
    fun inquireParticipant(@PathVariable participationId: Long): ParticipantData {
        return participantDataDao.findByParticipantId(participationId)
    }
}