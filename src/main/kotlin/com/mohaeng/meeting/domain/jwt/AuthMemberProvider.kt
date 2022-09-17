package com.mohaeng.meeting.domain.jwt

import com.mohaeng.meeting.presentation.model.AuthMember

/**
 * Created by ShinD on 2022/09/08.
 */
interface AuthMemberProvider {

    fun getAuthMember(authToken: AuthToken): AuthMember
}