package com.mohang.meeting.domain.member

import com.mohang.meeting.infrastructure.client.member.model.MemberData

/**
 * Created by ShinD on 2022/09/07.
 */
interface MemberServiceClient {

    fun getMember(memberId: Long): MemberData
}