package com.mohaeng.meeting.presentation.argumentresolver.auth.exception

/**
 * Created by ShinD on 2022/09/12.
 */
class BlacklistIsNoAuthorityException : RuntimeException(
    "블랙리스트는 접근 권한이 없습니다."
) {
}