package com.mohaeng.meeting.presentation

import com.mohaeng.meeting.application.facade.CreateMeetingFacade
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.presentation.argumentresolver.auth.Auth
import com.mohaeng.meeting.presentation.model.AuthMember
import com.mohaeng.meeting.presentation.model.CreateMeetingRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder.fromPath
import javax.validation.Valid

/**
 * Created by ShinD on 2022/09/08.
 */
@RestController
class CreateMeetingRestController(

    private val createMeetingFacade: CreateMeetingFacade,

    ) {

    /**
     * 모임 생성
     */
    @Log
    @PostMapping("/api/meeting")
    fun create(
        @Auth authMember: AuthMember,
        @Valid @RequestBody createMeetingRequest: CreateMeetingRequest,
    ): ResponseEntity<Unit> {

        val meetingId = createMeetingFacade.create(
            createMeetingDto = createMeetingRequest.toServiceMeetingDto(),
            createParticipationFormDto = createMeetingRequest.toServiceApplyFormDto(),
            createParticipantDto = createMeetingRequest.toServiceParticipantDto(memberId = authMember.id),
        )

        val uri = fromPath("/api/meeting/{meetingId}") // api/member/{memberId}
            .buildAndExpand(meetingId)      // api/member/10
            .toUri()

        return ResponseEntity.created(uri).build()
    }
}