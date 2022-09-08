package com.mohang.meeting.domain.meetingrole

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import com.mohang.meeting.domain.enums.MeetingAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "MEETING_ROLE")
class MeetingRole( // 모임 별로 정한 역할

    val name: String, // 역할의 이름 (ex: 회장)

    @Enumerated(STRING)
    val authority: MeetingAuthority,  // 해당 역할이 가질 수 있는 권한

    @Column(nullable = false)
    val meetingId: Long, // 어떤 모임에서 정해진 규칙인지
) : BaseEntity() {
}