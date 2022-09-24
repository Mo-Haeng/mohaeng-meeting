package com.mohaeng.meeting.domain.participant.presentation

import com.mohaeng.meeting.domain.meeting.presentation.model.AuthMember
import com.mohaeng.meeting.domain.participant.facade.RegisterParticipantFacade
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.global.web.argumentresolver.auth.Auth
import com.mohaeng.meeting.presentation.model.RegisterParticipantRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

/**
 * Created by ShinD on 2022/09/12.
 */
@RestController
class RegisterParticipantRestController(

    private val registerParticipantFacade: RegisterParticipantFacade,
) {

    @Log
    @PostMapping("/api/participate/meeting/{meetingId}")
    fun registerParticipant(
        @Auth accepter: AuthMember,
        @RequestBody registerParticipantRequest: RegisterParticipantRequest,
        @PathVariable(name = "meetingId") meetingId: Long,
    ): ResponseEntity<Unit> {

        val participantId = registerParticipantFacade.command(
            accepter = accepter,
            createParticipantDto = registerParticipantRequest.toServiceParticipantDto(),
            saveWrittenParticipationFormDto = registerParticipantRequest.toServiceWrittenApplyFormDto(),
            meetingId = meetingId
        )

        val uri = UriComponentsBuilder.fromPath("/api/participate/{participantId}")
            .buildAndExpand(participantId)
            .toUri()

        return ResponseEntity.created(uri).build()
    }
}