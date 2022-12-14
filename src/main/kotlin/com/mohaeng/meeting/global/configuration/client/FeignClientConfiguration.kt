package com.mohaeng.meeting.global.configuration.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.mohaeng.meeting.global.configuration.client.errordecoder.FeignClientErrorDecoder
import com.mohaeng.meeting.infrastructure.client.Client
import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar

/**
 * Created by ShinD on 2022/09/07.
 */
@Configuration
@EnableFeignClients(basePackageClasses = [Client::class])
class FeignConfiguration {

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
        return FeignClientErrorDecoder(objectMapper)
    }
}
