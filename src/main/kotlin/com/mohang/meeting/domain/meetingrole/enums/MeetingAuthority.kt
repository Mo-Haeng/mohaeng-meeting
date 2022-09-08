package com.mohang.meeting.domain.meetingrole.enums

/**
 * Created by ShinD on 2022/09/07.
 */
enum class MeetingAuthority {

    REPRESENTATIVE, // 모임의 대표, 모든 권한을 가짐, 회장과 같은 역할

    MANAGER, // 관리자, 일반 등급의 회원들에 대한 관리 가능, 부회장, 총무 등의 역할

    BASIC, // 일반 회원의 권한

}