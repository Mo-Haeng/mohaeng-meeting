package com.mohang.meeting.presentation

import com.mohang.meeting.application.meeting.CreateMeetingFacade
import com.mohang.meeting.presentation.argumentresolver.auth.Auth
import com.mohang.meeting.presentation.model.AuthMember
import com.mohang.meeting.presentation.model.CreateMeetingRequest
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
    @PostMapping("/api/meeting")
    fun create(
        @Auth authMember: AuthMember,
        @Valid @RequestBody createMeetingRequest: CreateMeetingRequest,
    ): ResponseEntity<Unit> {

        val meetingId = createMeetingFacade.create(
            memberId = authMember.id,
            createMeetingDto = createMeetingRequest.toServiceMeetingDto(),
            createApplyFormDto = createMeetingRequest.toServiceApplyFormDto(),
            createParticipantDto = createMeetingRequest.toServiceParticipantDto(),
        )

        val uri = fromPath("/api/meeting/{meetingId}") // api/member/{memberId}
            .buildAndExpand(meetingId)      // api/member/10
            .toUri()

        return ResponseEntity.created(uri).build()
    }
}