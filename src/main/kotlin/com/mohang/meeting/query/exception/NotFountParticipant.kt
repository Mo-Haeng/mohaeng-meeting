package com.mohang.meeting.query.exception

/**
 * Created by ShinD on 2022/09/13.
 */
class NotFountParticipant : RuntimeException(
    "회원이 가입된 모임을 찾을 수 없습니다."
) {
}