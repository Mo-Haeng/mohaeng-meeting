package com.mohang.meeting.application.meetingrole

import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/08.
 *
 * 오직 모임 생성 시에만 사용되며,
 * 이후 역할을 추가하거나, 변경하는 등의 작업은 다른 UseCase를 사용해야 함.
 *
 * 역할을 변경할 때 기존 역할을 모두 변경하는 것이 아닌 기존 역할을 남겨두고, 새로운 역할을 생성할 수 있는데, 그런 경우 기존 역할이 할당된 회원들의 처리가 어려워지므로 이렇게 구현함
 */
@Service
class CreateDefaultMeetingRoleUseCase(

    private val meetingRoleRepository: MeetingRoleRepository,

) {

    /**
     * 기본 역할인 대표와, 일반 회원 역할을 추가한다.
     */
    fun createAndReturnRepresentativeRoleId(meetingId: Long): Long {
        val representativeRole = MeetingRole.representativeRole(meetingId)
        val basicRole = MeetingRole.basicRole(meetingId)

        return meetingRoleRepository.saveAll(listOf(representativeRole, basicRole))[0].id!!
    }
}