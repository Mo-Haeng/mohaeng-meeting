package com.mohang.meeting.infrastructure.persistence

import com.mohang.meeting.domain.meetingrole.enums.MeetingAuthority
import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.fixture.MeetingFixture
import com.mohang.meeting.infrastructure.persistence.meeting.MeetingRepository
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import strikt.api.expectThat
import strikt.assertions.isEqualTo

/**
 * Created by ShinD on 2022/09/08.
 */
@DataJpaTest
class MeetingRoleRepositoryTest {

    @Autowired
    private lateinit var meetingRoleRepository: MeetingRoleRepository
    @Autowired
    private lateinit var meetingRepository: MeetingRepository

    @Test
    fun `saveAll의 반환은 저장 순서대로`() {

        //given
        val save = meetingRepository.save(MeetingFixture.meeting())
        val meetingRole = MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = save.id!!)
        val listOf = listOf(
            meetingRole,
            MeetingRole(name = "일반", authority = MeetingAuthority.MANAGER, meetingId = save.id!!),
            MeetingRole(name = "바보", authority = MeetingAuthority.BASIC, meetingId = save.id!!),
        )

        //when
        val saveAll = meetingRoleRepository.saveAll(listOf)

        //then
        expectThat(saveAll[0]){
            isEqualTo(meetingRole)
        }
    }
}