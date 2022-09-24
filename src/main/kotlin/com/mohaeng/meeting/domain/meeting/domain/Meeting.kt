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

    private val name: String, // 모임의 이름

    private val mainImagePath: String? = null,

    @Lob
    private val description: String, // 모임 설명

    private val capacity: Int, // 모임 최대 인원 (-1은 제한 없음을 의미한다.)

) : BaseEntity() {

}