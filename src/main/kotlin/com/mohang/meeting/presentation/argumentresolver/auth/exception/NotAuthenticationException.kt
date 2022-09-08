package com.mohang.meeting.presentation.argumentresolver.auth.exception

import java.lang.RuntimeException

/**
 * Created by ShinD on 2022/09/09.
 */
class NotAuthenticationException(
    message: String,
) : RuntimeException(message) {
}