package com.mohang.meeting.infrastructure.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.mohang.meeting.configuration.jwt.JwtConfiguration.Companion.MEMBER_ID_CLAIM
import com.mohang.meeting.configuration.jwt.JwtConfiguration.Companion.MEMBER_ROLE_CLAIM
import com.mohang.meeting.domain.jwt.AuthMemberProvider
import com.mohang.meeting.domain.jwt.AuthToken
import com.mohang.meeting.infrastructure.jwt.properties.JwtProperties
import com.mohang.meeting.presentation.model.AuthMember
import com.mohang.meeting.presentation.model.Role

/**
 * Created by ShinD on 2022/09/08.
 */
class AuthMemberProviderImpl(

    jwtProperties: JwtProperties,

): AuthMemberProvider {


    // JWT 암호 알고리즘
    private val algorithm: Algorithm by lazy {
        Algorithm.HMAC512(jwtProperties.secretKey)
    }

    override fun getAuthMember(authToken: AuthToken): AuthMember {

            val jwt          =   JWT.require(algorithm).build().verify(authToken.token)
            val id           =   jwt.getClaim(MEMBER_ID_CLAIM).toString().replace("\"", "")
            val role         =   jwt.getClaim(MEMBER_ROLE_CLAIM).toString().replace("\"", "")

        return AuthMember(id = id.toLong(), role = Role.valueOf(role))
    }
}