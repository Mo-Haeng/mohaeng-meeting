package com.mohang.meeting.fixture

import com.mohang.meeting.domain.participant.Participant
import javax.persistence.Column

/**
 * Created by ShinD on 2022/09/08.
 */
object ParticipantFixture {

    private const val MEMBER_ID = 1L
    private const val NICKNAME = "nickname"
    private const val PROFILE_IMAGE_PATH = "profile"
    private const val MEETING_ID = 10L
    private const val MEETING_ROLE_ID = 12L

    fun participant(
        memberId: Long = MEMBER_ID,
        nickname: String = NICKNAME,
        profileImagePath: String? = PROFILE_IMAGE_PATH,
        meetingId: Long = MEETING_ID,
        meetingRoleId: Long = MEETING_ROLE_ID,
    ) =
        Participant(
            memberId = memberId,
            nickname = nickname,
            profileImagePath = profileImagePath,
            meetingId = meetingId,
            meetingRoleId = meetingRoleId,
        )
}