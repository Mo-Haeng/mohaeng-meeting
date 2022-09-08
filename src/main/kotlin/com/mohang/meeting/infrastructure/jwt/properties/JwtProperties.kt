package com.mohang.meeting.infrastructure.jwt.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Created by ShinD on 2022/09/08.
 */
@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperties (

    val secretKey: String,

)