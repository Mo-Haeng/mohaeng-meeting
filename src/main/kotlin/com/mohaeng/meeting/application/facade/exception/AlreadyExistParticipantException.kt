package com.mohaeng.meeting.application.facade.exception

/**
 * Created by ShinD on 2022/09/13.
 */
class AlreadyExistParticipantException : RuntimeException(
    "이미 모임에 가입한 참가자입니다."
) {
}