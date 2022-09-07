package com.mohang.meeting.domain.meeting

import com.mohang.meeting.configuration.jpa.BaseEntity

/**
 * Created by ShinD on 2022/09/07.
 */
import javax.persistence.*
import javax.persistence.CascadeType.ALL

@Entity
@Table(name = "MEETING")
class Meeting(

    private val name: String, // 모임의 이름

    @Lob
    private val description: String, // 모임 설명

    private val capacity: Int, // 모임 최대 인원 (-1은 제한 없음을 의미한다.)

    // 모임의 역할들, (Meeting이 Aggregate Root)
    @OneToMany(mappedBy = "meeting", orphanRemoval = true, cascade = [ALL])
    private val meetingRoles: MutableList<MeetingRole> = mutableListOf(),

    // 모임에 가입하기 위해 작성해야 할 가입 신청서 양식 (수정 시, 기존 것을 지우는 것이 아닌 새로 만드는 식으로 관리하여 리스트로 설정하였음)
    @OneToMany(mappedBy = "meeting", orphanRemoval = true, cascade = [ALL])
    private val applyForms: MutableList<ApplyForm> = mutableListOf(),

    /**
     * 모임 참가자와는 동일한 생명주기를 갖지 않으므로
     * 다른 Aggregate라고 판단하였음
     */

) : BaseEntity() {

}