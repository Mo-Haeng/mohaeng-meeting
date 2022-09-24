package com.mohaeng.meeting.domain.participant.exception

/**
 * Created by ShinD on 2022/09/25.
 */
class MeetingIsFullException : RuntimeException(
    "모임이 더 이상 회원을 받을 수 없습니다."
) {
}