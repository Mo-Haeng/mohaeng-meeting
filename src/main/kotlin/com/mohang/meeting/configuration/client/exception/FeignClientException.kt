package com.mohang.meeting.configuration.client.exception

import com.mohang.meeting.configuration.exception.ExceptionResponse

/**
 * Created by ShinD on 2022/09/08.
 */
class FeignClientException(
    private val exceptionResponse: ExceptionResponse
) : RuntimeException(){
}