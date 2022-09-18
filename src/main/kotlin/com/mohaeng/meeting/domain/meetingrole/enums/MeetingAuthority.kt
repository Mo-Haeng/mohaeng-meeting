package com.mohaeng.meeting.domain.meetingrole.enums

import com.mohaeng.meeting.application.facade.exception.NoAuthorityAcceptParticipationRequestException
import com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority.*

/**
 * Created by ShinD on 2022/09/07.
 */
enum class MeetingAuthority {

    REPRESENTATIVE, // 모임의 대표, 모든 권한을 가짐, 회장과 같은 역할

    MANAGER, // 관리자, 일반 등급의 회원들에 대한 관리 가능, 부회장, 총무 등의 역할

    BASIC, // 일반 회원의 권한
    ;

    /**
     * 권한이 매니저 이상인지 확인
     */
    fun isMoreThanManager(): Boolean {
        return when (this) {
            REPRESENTATIVE -> true
            MANAGER -> true
            BASIC -> false
        }
    }
}