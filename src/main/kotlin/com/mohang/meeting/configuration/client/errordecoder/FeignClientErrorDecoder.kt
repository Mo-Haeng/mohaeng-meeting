package com.mohang.meeting.configuration.client.errordecoder

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import com.mohang.meeting.configuration.client.exception.FeignClientException
import com.mohang.meeting.configuration.exception.ExceptionResponse
import feign.Response
import feign.codec.ErrorDecoder
import feign.codec.StringDecoder
import mu.KotlinLogging


class FeignClientErrorDecoder(

    private val objectMapper: ObjectMapper,

) : ErrorDecoder {

    private val stringDecoder: StringDecoder = StringDecoder()
    private val log = KotlinLogging.logger {  }

    override fun decode(methodKey: String, response: Response): Exception {

        return response.body()?.let {

            val message = stringDecoder.decode(response, String::class.java).toString()

            try {
                val errorResponse = objectMapper.readValue(message, ExceptionResponse::class.java)
                FeignClientException(errorResponse)
            } catch (e: JsonParseException) {
                log.error { message }
                FeignClientException(ExceptionResponse(code = 9999, message = "서비스에 장애가 있어 요청을 수행할 수 없습니다."))
            }
        }!!
    }
}