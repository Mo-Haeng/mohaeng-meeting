package com.mohaeng.meeting.configuration.client.exception

import com.mohaeng.meeting.configuration.exception.ExceptionResponse

/**
 * Created by ShinD on 2022/09/08.
 */
class FeignClientException(
    val exceptionResponse: ExceptionResponse,
) : RuntimeException() {
}