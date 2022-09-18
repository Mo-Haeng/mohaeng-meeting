package com.mohaeng.meeting.domain.meetingrole

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.configuration.jpa.BaseEntity
import com.mohaeng.meeting.domain.meetingrole.enums.MeetingAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "MEETING_ROLE")
class MeetingRole(
    // 모임 별로 정한 역할

    val name: String, // 역할의 이름 (ex: 회장)

    @Enumerated(STRING)
    val authority: MeetingAuthority,  // 해당 역할이 가질 수 있는 권한

    @Column(nullable = false)
    val meetingId: Long, // 어떤 모임에서 정해진 규칙인지


    val isParticipantDefault: Boolean = false,// 가입 신청 시 기본적으로 적용되는 역할인지에 대한 여부, 오직 하나만이 default 옵션이 될 수 있다.

) : BaseEntity() {

    companion object {

        // Default Role 정의
        fun representativeRole(meetingId: Long) =
            MeetingRole(name = "대표", authority = MeetingAuthority.REPRESENTATIVE, meetingId = meetingId)

        fun basicRole(meetingId: Long) =
            MeetingRole(
                name = "일반",
                authority = MeetingAuthority.BASIC,
                meetingId = meetingId,
                isParticipantDefault = true
            ) // 기본 옵션
    }
}