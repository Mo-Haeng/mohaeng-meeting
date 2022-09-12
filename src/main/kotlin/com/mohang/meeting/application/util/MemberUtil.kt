package com.mohang.meeting.application.util

import com.mohang.meeting.infrastructure.client.member.model.MemberData
import com.mohang.meeting.infrastructure.client.member.model.Role

/**
 * Created by ShinD on 2022/09/11.
 */
class MemberUtil {

    companion object {

        fun ifBlacklistThrowException(memberData: MemberData, exception: RuntimeException) {
            if (Role.isBlack(memberData.role)) throw exception
        }
    }
}