package com.mohang.meeting.configuration.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.mohang.meeting.infrastructure.client.Client
import com.mohang.meeting.infrastructure.client.errordecoder.FeignClientExceptionErrorDecoder
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

}
