package com.mohang.meeting.application.participant.exception

/**
 * Created by ShinD on 2022/09/11.
 */
class NoAuthorityParticipatingMeeting : RuntimeException(
    "모임에 참가할 권한이 없습니다."
) {
}