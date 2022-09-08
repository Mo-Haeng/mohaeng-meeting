package com.mohang.meeting.presentation.argumentresolver.auth

import com.mohang.meeting.domain.jwt.AuthMemberProvider
import com.mohang.meeting.domain.jwt.AuthToken
import com.mohang.meeting.presentation.argumentresolver.auth.exception.NotAuthenticationException
import com.mohang.meeting.presentation.model.AuthMember
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * Created by ShinD on 2022/09/08.
 */
@Component
class AuthMemberArgumentResolver(

    private val authMemberProvider: AuthMemberProvider,

) : HandlerMethodArgumentResolver {

    companion object {
        const val AUTH_TOKEN_HEADER_NAME = "Authorization"
        const val AUTH_TOKEN_HEADER_PREFIX = "Bearer "
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {

        val hasAuthAnnotation = parameter.hasParameterAnnotation(Auth::class.java)

        val hasAuthMemberPrincipleType = AuthMember::class.java.isAssignableFrom(parameter.parameterType)

        return hasAuthAnnotation && hasAuthMemberPrincipleType
    }





    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): AuthMember? {

        //AUTH_TOKEN_HEADER_NAME(Authorization) 이 없는 경우 예외
        val authTokenValue = webRequest.getHeader(AUTH_TOKEN_HEADER_NAME)
            ?: throw NotAuthenticationException("Auth Token이 없어 인증할 수 없습니다.")

        //AUTH_TOKEN_HEADER_PREFIX(Bearer )로 시작하지 않는 경우 예외
        if (!authTokenValue.startsWith(AUTH_TOKEN_HEADER_PREFIX))
            throw NotAuthenticationException("Auth Token의 형식이 잘못되었습니다. (Bearer 붙지 않음)")

        //accessToken 추출 (Bearer 제거)
        val authToken = authTokenValue.replace(AUTH_TOKEN_HEADER_PREFIX, "").trim()

        return authMemberProvider.getAuthMember(AuthToken(authToken))
    }
}