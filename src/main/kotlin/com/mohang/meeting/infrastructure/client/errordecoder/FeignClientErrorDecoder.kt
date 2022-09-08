package com.mohang.meeting.infrastructure.client.errordecoder

import com.fasterxml.jackson.databind.ObjectMapper
import com.mohang.meeting.configuration.client.exception.FeignClientException
import com.mohang.meeting.configuration.exception.ExceptionResponse
import feign.Response
import feign.codec.ErrorDecoder
import feign.codec.StringDecoder

/**
 * Created by ShinD on 2022/09/08.
 */
class FeignClientErrorDecoder(

    private val objectMapper: ObjectMapper,

) : ErrorDecoder {

    private val stringDecoder: StringDecoder = StringDecoder()
    private val errorDecoder: ErrorDecoder = ErrorDecoder.Default()

    override fun decode(methodKey: String, response: Response): Exception {

        return response.body()?.let {

            val message = stringDecoder.decode(response, String::class.java).toString()

            val errorResponse = objectMapper.readValue(message, ExceptionResponse::class.java)

            FeignClientException(errorResponse)
        }!!
    }
}