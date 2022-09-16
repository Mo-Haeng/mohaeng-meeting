package com.mohaeng.meeting.infrastructure.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mohaeng.meeting.configuration.jwt.JwtConfiguration.Companion.MEMBER_ID_CLAIM
import com.mohaeng.meeting.configuration.jwt.JwtConfiguration.Companion.MEMBER_ROLE_CLAIM
import com.mohaeng.meeting.domain.jwt.AuthMemberProvider
import com.mohaeng.meeting.domain.jwt.AuthToken
import com.mohaeng.meeting.infrastructure.jwt.properties.JwtProperties
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.presentation.model.AuthMember
import com.mohaeng.meeting.presentation.model.Role

/**
 * Created by ShinD on 2022/09/08.
 */
open class AuthMemberProviderImpl(

    jwtProperties: JwtProperties,

    ) : AuthMemberProvider {

    // JWT 암호 알고리즘
    private val algorithm: Algorithm by lazy {
        Algorithm.HMAC512(jwtProperties.secretKey)
    }

    @Log
    override fun getAuthMember(authToken: AuthToken): AuthMember {

        val jwt = JWT.require(algorithm).build().verify(authToken.token)
        val id = jwt.getClaim(MEMBER_ID_CLAIM).toString().replace("\"", "")
        val role = jwt.getClaim(MEMBER_ROLE_CLAIM).toString().replace("\"", "")

        return AuthMember(id = id.toLong(), role = Role.valueOf(role))
    }
}