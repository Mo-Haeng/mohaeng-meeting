package com.mohaeng.meeting.domain.meeting.presentation.model

/**
 * Created by ShinD on 2022/09/25.
 */
data class AuthMember(

    val id: Long,

    val role: Role,
)

enum class Role {

    ADMIN,

    BASIC,

    BLACK,;

    companion object {

        fun isBlack(role: Role): Boolean {
            return role == BLACK
        }
    }
}