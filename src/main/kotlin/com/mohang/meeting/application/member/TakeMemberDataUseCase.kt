package com.mohang.meeting.application.member

import com.mohang.meeting.domain.member.MemberServiceClient
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 */
@Service
class TakeMemberDataUseCase(

    private val memberServiceClient: MemberServiceClient, //FeignClient

) {

    fun command(memberId: Long) =
        memberServiceClient.getMember(memberId)
}