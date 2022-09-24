package com.mohaeng.meeting.global.configuration.client.exception

import com.mohaeng.meeting.global.configuration.exception.ExceptionResponse

/**
 * Created by ShinD on 2022/09/08.
 */
class FeignClientException(
    val exceptionResponse: ExceptionResponse,
) : RuntimeException() {
}