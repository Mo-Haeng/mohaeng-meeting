package com.mohang.meeting.query.exception

/**
 * Created by ShinD on 2022/09/11.
 */
class NotFoundDefaultRole : RuntimeException(
    "모임의 기본 역할을 찾을 수 없습니다. (존재하지 않는 모임일 가능성이 큽니다.)"
) {
}