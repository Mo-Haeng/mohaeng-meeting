package com.mohang.meeting.application.meeting

import com.mohang.meeting.application.meeting.dto.CreateApplyFormDto
import com.mohang.meeting.application.meeting.dto.CreateMeetingDto
import com.mohang.meeting.application.meeting.exception.NotAuthorityCreateMeeting
import com.mohang.meeting.domain.enums.MeetingAuthority
import com.mohang.meeting.domain.meetingrole.MeetingRole
import com.mohang.meeting.domain.participant.Participant
import com.mohang.meeting.domain.member.MemberServiceClient
import com.mohang.meeting.infrastructure.client.member.model.MemberData
import com.mohang.meeting.infrastructure.client.member.model.Role.Companion.isBlack
import com.mohang.meeting.infrastructure.eventproducer.EventProducer
import com.mohang.meeting.infrastructure.persistence.applyform.ApplyFormRepository
import com.mohang.meeting.infrastructure.persistence.meeting.MeetingRepository
import com.mohang.meeting.infrastructure.persistence.meetingrole.MeetingRoleRepository
import com.mohang.meeting.infrastructure.persistence.participant.ParticipantRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

/**
 * Created by ShinD on 2022/09/07.
 */
@Service
class CreateMeetingUseCase(

    private val meetingRepository: MeetingRepository,

    private val meetingRoleRepository: MeetingRoleRepository,

    private val participantRepository: ParticipantRepository,

    private val applyFormRepository: ApplyFormRepository,

    private val memberServiceClient: MemberServiceClient, //FeignClient

    private val transaction: TransactionTemplate,

    private val eventProducer: EventProducer,
) {

    fun command(
        memberId: Long,
        createMeetingDto: CreateMeetingDto,
        createApplyFormDto: CreateApplyFormDto
    ): Long {

        // Member Service로부터 회원 정보 조회
        val memberData = getMemberAndCheckAuthority(memberId)

        // 요청 데이터를 기반으로 meeting 생성
        val meeting = createMeetingDto.toEntity()

        return transaction.execute {
            // 미팅 저장
            val savedMeetingId = meetingRepository.save(meeting).id!!

            // 모임의 대표 역할과 일반 회원 역할 설정
            val representativeRoleId =
                saveDefaultMeetingRoleAndReturnRepresentativeRoleId(savedMeetingId)

            // 신청 회원을 모임 참가자(대표)로 설정
            participantRepository.save(
                Participant(memberId = memberId, nickname = memberData.nickname, profileImagePath = memberData.profileImagePath,
                    meetingId = savedMeetingId, meetingRoleId = representativeRoleId)
            )

            // 가입 신청서 양식 저장
            applyFormRepository.save(createApplyFormDto.toEntity(memberId))

            // 이벤트 전송
            eventProducer.send("create meeting event", savedMeetingId)

            savedMeetingId // return
        }!!
    }

    /**
     * FeignClient를 사용하여 회원 정보를 조회한 이후 블랙리스트인지 체크한 뒤 반환
     */
    private fun getMemberAndCheckAuthority(memberId: Long): MemberData {

        // FeignClient를 사용하여 회원 정보 받아오기
        return memberServiceClient.getMember(memberId).also {
            // 블랙리스트인 경우 모임 생성 불가능
            if (isBlack(it.role)) throw NotAuthorityCreateMeeting()
        }
    }

    /**
     * BASIC 역할과 REPRESENTATIVE 역할을 저장하고 REPRESENTATIVE의 ID를 반환한다.
     */
    private fun saveDefaultMeetingRoleAndReturnRepresentativeRoleId(savedMeetingId: Long): Long {
        val representativeRole = registerRepresentativeRole(savedMeetingId)
        val basicRole = registerBasicRole(savedMeetingId)

        return meetingRoleRepository.saveAll(listOf(representativeRole, basicRole))[0].id!!
    }

    private fun registerRepresentativeRole(meetingId: Long) =
        MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = meetingId)

    private fun registerBasicRole(meetingId: Long) =
        MeetingRole(name = "일반", authority = MeetingAuthority.BASIC, meetingId = meetingId)
}