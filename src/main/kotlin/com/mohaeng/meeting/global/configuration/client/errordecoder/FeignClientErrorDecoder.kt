package com.mohaeng.meeting.global.configuration.client.errordecoder

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.mohaeng.meeting.global.configuration.client.exception.FeignClientException
import com.mohaeng.meeting.global.configuration.exception.ExceptionResponse
import com.mohaeng.meeting.global.aop.log.Log
import feign.Response
import feign.codec.ErrorDecoder
import feign.codec.StringDecoder
import mu.KotlinLogging

open class FeignClientErrorDecoder(

    private val objectMapper: ObjectMapper,

    ) : ErrorDecoder {

    private val stringDecoder: StringDecoder = StringDecoder()
    private val log = KotlinLogging.logger { }

    @Log
    override fun decode(methodKey: String, response: Response): Exception {

        return response.body()?.let {

            val message = stringDecoder.decode(response, String::class.java).toString()

            try {
                val errorResponse = objectMapper.readValue(message, ExceptionResponse::class.java)
                FeignClientException(errorResponse)
            }
            catch (e: JsonParseException) {
                log.error { message }
                FeignClientException(ExceptionResponse(code = 9999, message = "서비스에 장애가 있어 요청을 수행할 수 없습니다."))
            }
        }!!
    }
}