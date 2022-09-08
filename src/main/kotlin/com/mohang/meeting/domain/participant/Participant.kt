package com.mohang.meeting.domain.participant

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "PARTICIPANT")
class Participant( // 모임에 참여한 참가자

    private val memberId: Long, // 해당 참가자의 회원 ID

    private val nickname: String, // 모임에서 사용할 별명

    private val profileImagePath: String?, // 모임에서 사용할 프로필사진 url

    @Column(nullable = false)
    private val meetingId: Long, // 모임 ID, Meeting과 Participant는 다른 Aggregate이므로 ID를 이용한 간접 참조만 사용하였음

    @Column(nullable = false)
    private val meetingRoleId: Long, // 모임에서의 역할 ID, 해당 회원이 모임에서 어떤 역할인지
) : BaseEntity() {

}