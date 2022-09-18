package com.mohaeng.meeting.configuration.jwt

import com.mohaeng.meeting.domain.jwt.AuthMemberProvider
import com.mohaeng.meeting.infrastructure.jwt.AuthMemberProviderImpl
import com.mohaeng.meeting.infrastructure.jwt.properties.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ShinD on 2022/09/08.
 */
@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfiguration {

    companion object {

        const val AUTH_TOKEN_SUBJECT = "AuthToken"
        const val MEMBER_ID_CLAIM = "memberId"
        const val MEMBER_ROLE_CLAIM = "memberRole"
    }

    @Bean
    fun extractAuthMemberFromAuthTokenUseCase(jwtProperties: JwtProperties): AuthMemberProvider {
        return AuthMemberProviderImpl(jwtProperties)
    }
}