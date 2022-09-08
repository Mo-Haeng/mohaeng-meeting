package com.mohang.meeting.configuration.client

import com.mohang.meeting.infrastructure.client.Client
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

/**
 * Created by ShinD on 2022/09/07.
 */
@Configuration
@EnableFeignClients(basePackageClasses = [Client::class])
class FeignConfiguration {

}
