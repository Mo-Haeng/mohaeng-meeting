package com.mohang.meeting.domain.meeting

/**
 * Created by ShinD on 2022/09/07.
 */
import com.mohang.meeting.configuration.jpa.BaseEntity
import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "MEETING")
class Meeting(

    private val name: String, // 모임의 이름

    @Lob
    private val description: String, // 모임 설명

    private val capacity: Int, // 모임 최대 인원 (-1은 제한 없음을 의미한다.)


    /**
     * 모임 참가자와는 동일한 생명주기를 갖지 않으므로
     * 다른 Aggregate라고 판단하였음
     */

) : BaseEntity() {

}