//package com.mohang.meeting.infrastructure.client.member
//
//import com.mohang.meeting.configuration.client.MemberClientConfiguration
//import com.mohang.meeting.domain.member.MemberServiceClient
//import com.mohang.meeting.infrastructure.client.member.model.MemberData
//import org.springframework.cloud.openfeign.FeignClient
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//
//
//@FeignClient(name = "mohang-member-service", configuration = [MemberClientConfiguration::class]) // mohang-member-service 를 호출한다
//interface MemberServiceFeignClient : MemberServiceClient {
//
//    /**
//     * Member Service로부터 회원 정보 얻어온다
//     */
//    @GetMapping("/api/member/{memberId}")
//    override fun getMember(
//        //@RequestHeader("Authorization") bearerToken: String,
//        @PathVariable(name = "memberId") memberId: Long)
//    : MemberData
//}