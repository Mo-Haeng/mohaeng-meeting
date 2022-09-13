package com.mohang.meeting.presentation.advice

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.mohang.meeting.configuration.client.exception.FeignClientException
import com.mohang.meeting.configuration.exception.ExceptionResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Created by ShinD on 2022/09/09.
 */
@RestControllerAdvice
class ExceptionControllerAdvice {

    private val log = KotlinLogging.logger { }

    @ExceptionHandler(JWTVerificationException::class, JWTDecodeException::class)
    fun handleJWTException(ex: Exception): ResponseEntity<ExceptionResponse> {

        log.error {
            """
            [ERROR] : [${ex::class.java.name}]
            [CAUSE] : [Auth Token이 만료되었거나 잘못되었습니다.]
            [MESSAGE] : [${ex.message}]
            [STACK TRACE] : ${ex.stackTraceToString()}
            
            """
        }

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse(code = 401, message = "Auth Token이 만료되었거나 잘못되었습니다."))
    }



    @ExceptionHandler(FeignClientException::class)
    fun handleFeignException(ex: FeignClientException): ResponseEntity<ExceptionResponse> {

        log.error { "다른 서버와 요청을 주고받던 중 오류가 발생했습니다. \n - message : [${ex.exceptionResponse.message}]\n - code : [${ex.exceptionResponse.code}]" }

        log.error {
            """
            [ERROR] : [${ex::class.java.name}]
            [CAUSE] : [다른 서버와 요청을 주고받던 중 오류가 발생했습니다.]
            [MESSAGE] : [${ex.message}]
            [STACK TRACE] : ${ex.stackTraceToString()}
            
            """
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.exceptionResponse)
    }



    @ExceptionHandler(BindException::class, HttpMessageNotReadableException::class)
    fun handleBindException(ex: Exception): ResponseEntity<ExceptionResponse> {

        log.error {
            """
            [ERROR] : [${ex::class.java.name}]
            [CAUSE] : [Json 혹은 요청 파라미터의 형식이 올바르지 않습니다.]
            [MESSAGE] : [${ex.message}]
            [STACK TRACE] : ${ex.stackTraceToString()}
            
            """
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(code = 400, message = "Json 혹은 요청 파라미터의 형식이 올바르지 않습니다."))
    }



    @ExceptionHandler(RuntimeException::class)
    fun handleException(ex: RuntimeException): ResponseEntity<ExceptionResponse> {

        log.error {
            """
            [ERROR] : [${ex::class.java.name}]
            [CAUSE] : [${ex.cause}]
            [MESSAGE] : [${ex.message}]
            [STACK TRACE] : ${ex.stackTraceToString()}
            
            """
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(code = 400, message = ex.message ?: "예외 발생"))
    }



    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ExceptionResponse> {

        log.error {
            """
            [ERROR] : [${ex::class.java.name}]
            [CAUSE] : [예측하지 못한 예외 발생. - ${ex.cause}]
            [MESSAGE] : [${ex.message}]
            [STACK TRACE] : ${ex.stackTraceToString()}
            
            """
        }
        return ResponseEntity
            .status(500)
            .body(ExceptionResponse(code = 500, message = ex.message ?: "예측하지 못한 예외가 발생하였습니다."))
    }
}