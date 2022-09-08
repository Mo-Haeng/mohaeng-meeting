package com.mohang.meeting.presentation.model

import com.mohang.meeting.infrastructure.client.member.model.Role

/**
 * Created by ShinD on 2022/09/08.
 */
data class AuthMember(

    val id: Long,

    val role: Role,
)