package com.mohang.meeting.fixture

import com.mohang.meeting.infrastructure.client.member.model.MemberData
import com.mohang.meeting.infrastructure.client.member.model.Role

/**
 * Created by ShinD on 2022/09/08.
 */
object FeignClientFixture {

    private const val MEMBER_ID = 1L
    private val MEMBER_ROLE = Role.BASIC
    private const val OAUTH2_TYPE = "NONE"
    private const val USERNAME: String = "sample username"
    private const val NAME: String = "sample name"
    private const val NICKNAME: String = "sample nickname"
    private const val EMAIL: String = "sample email"
    private const val PROFILE_IMAGE_PATH: String = "sample profileImagePath"
    private const val POINT = 0
    private const val CREATE_AT = "2000-00-00"
    private const val MODIFIED_AT = "2000-00-00"



    fun memberData(

        id: Long = MEMBER_ID,
        role: Role = MEMBER_ROLE,
        oauth2Type: String = OAUTH2_TYPE,
        username: String? = USERNAME,
        name: String = NAME,
        nickname: String = NICKNAME,
        email: String? = EMAIL,
        profileImagePath: String? = PROFILE_IMAGE_PATH,
        point: Int = POINT,
        createdAt: String = CREATE_AT,
        modifiedAt: String = MODIFIED_AT,
    ): MemberData {
        return MemberData(
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
}