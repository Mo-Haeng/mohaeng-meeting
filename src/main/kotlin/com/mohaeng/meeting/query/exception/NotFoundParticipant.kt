package com.mohaeng.meeting.query.exception

/**
 * Created by ShinD on 2022/09/13.
 */
class NotFoundParticipant : RuntimeException(
    "회원이 가입된 모임을 찾을 수 없습니다. (존재하지 않는 모임이거나, 회원이 모임에 가입되지 않았습니다.)"
) {
}