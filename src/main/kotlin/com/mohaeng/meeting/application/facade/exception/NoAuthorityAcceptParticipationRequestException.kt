package com.mohaeng.meeting.application.facade.exception

/**
 * Created by ShinD on 2022/09/13.
 */
class NoAuthorityAcceptParticipationRequestException : RuntimeException(
    "참가 신청을 승인할 권한이 없습니다."
) {
}