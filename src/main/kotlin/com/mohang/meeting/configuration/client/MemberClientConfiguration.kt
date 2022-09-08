package com.mohang.meeting.configuration.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.mohang.meeting.infrastructure.client.errordecoder.FeignClientExceptionErrorDecoder
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar

/**
 * Created by ShinD on 2022/09/07.
 */
@Configuration
class MemberClientConfiguration {

    @Bean
    fun localDateFeignFormatterRegister(): FeignFormatterRegistrar {
        return FeignFormatterRegistrar { registry: FormatterRegistry ->
            val registrar = DateTimeFormatterRegistrar()
            registrar.setUseIsoFormat(true)
            registrar.registerFormatters(registry)
        }
    }

    @Bean
    fun retryer(): Retryer {
        //return Retryer.NEVER_RETRY
        return Retryer.Default()
    }

    @Bean
    fun errorDecoder(
        objectMapper: ObjectMapper? = null,
    ): ErrorDecoder {
        checkNotNull(objectMapper) { "objectMapper is null" }
        return FeignClientExceptionErrorDecoder(objectMapper)
    }
}