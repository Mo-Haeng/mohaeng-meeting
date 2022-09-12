package com.mohang.meeting.application.participant.exception

/**
 * Created by ShinD on 2022/09/12.
 */
class NotMatchApplyFormException : RuntimeException(
    "작성해야 할 가입 신청서가 새로 생성되었거나 변경되었습니다."
) {
}