package com.mohang.meeting.domain.jwt

import com.mohang.meeting.presentation.model.AuthMember

/**
 * Created by ShinD on 2022/09/08.
 */
interface AuthMemberProvider {
    fun getAuthMember(authToken: AuthToken): AuthMember
}