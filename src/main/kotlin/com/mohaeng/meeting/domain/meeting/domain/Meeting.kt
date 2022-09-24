package com.mohaeng.meeting.domain.meeting.domain

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohaeng.meeting.global.configuration.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "MEETING")
class Meeting(

    val name: String, // 모임의 이름

    val mainImagePath: String? = null,

    @Lob
    val description: String, // 모임 설명

    val capacity: Int, // 모임 최대 인원

    var numberOfParticipants: Int = 0// 현재 가입한 회원 수 ( 회원을 참여시킬 때 가입 인원 한계 빠르게 파악하기 위함 )

) : BaseEntity() {

    fun addParticipantCount() {
        this.numberOfParticipants ++
    }

    fun isFull(): Boolean {
       return capacity <= numberOfParticipants
    }

}