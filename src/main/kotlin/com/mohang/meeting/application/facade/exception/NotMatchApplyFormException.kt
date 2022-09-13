package com.mohang.meeting.application.facade.exception

/**
 * Created by ShinD on 2022/09/12.
 */
class NotMatchApplyFormException : RuntimeException(
    "가입 신청서 양식이 새로 생성되었거나 변경되었습니다."
) {
}