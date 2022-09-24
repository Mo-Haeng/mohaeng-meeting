package com.mohaeng.meeting.domain.writtenparticipationform.exception

/**
 * Created by ShinD on 2022/09/18.
 */
class NoExistParticipationFormException : RuntimeException(
    "참가 신청서 양식이 존재하지 않습니다."
) {
}