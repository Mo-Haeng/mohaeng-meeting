package com.mohaeng.meeting.query.exception

/**
 * Created by ShinD on 2022/09/11.
 */
class NotFoundMeetingException : RuntimeException(
    "모임이 존재하지 않습니다."
) {
}