package com.mohang.meeting.fixture

import com.mohang.meeting.infrastructure.client.member.model.MemberData
import com.mohang.meeting.infrastructure.client.member.model.Role

/**
 * Created by ShinD on 2022/09/08.
 */
object MemberDataFixture {
    
    private const val ID = 1L
    private const val OAUTH2_TYPE = "NONE"
    private const val USERNAME = "username"
    private const val NAME = "name"
    private const val NICKNAME = "nickname"
    private const val EMAIL = "email"
    private const val PROFILE_IMAGE_PATH = "profile"
    private const val POINT = 10
    private const val CREATED_AT = "2000-10-04"
    private const val MODIFIED_AT = "2000-10-04"

    fun memberData(
        id: Long = ID,
        role: Role = Role.BASIC,
        oauth2Type: String = OAUTH2_TYPE,
        username: String? = USERNAME,
        name: String = NAME,
        nickname: String = NAME,
        email: String? = EMAIL,
        profileImagePath: String? = PROFILE_IMAGE_PATH,
        point: Int = POINT,
        createdAt: String = CREATED_AT,
        modifiedAt: String = MODIFIED_AT,
    ) =
        MemberData(
            id = id,
            role = role,
            oauth2Type = oauth2Type,
            username = username,
            name = name,
            nickname = nickname,
            email = email,
            profileImagePath = profileImagePath,
            point = point,
            createdAt = createdAt,
            modifiedAt = modifiedAt
        )
}