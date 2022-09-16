package com.mohaeng.meeting.application.usecase.meeting.exception

/**
 * Created by ShinD on 2022/09/07.
 */
class NoAuthorityCreateMeeting: RuntimeException(
    "모임을 생성할 권한이 없습니다. (블랙리스트)"
) {
}