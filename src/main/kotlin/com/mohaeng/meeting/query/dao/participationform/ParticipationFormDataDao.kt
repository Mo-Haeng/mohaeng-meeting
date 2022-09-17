package com.mohaeng.meeting.query.dao.participationform

import com.mohaeng.meeting.query.data.participationform.ParticipationFormData

/**
 * Created by ShinD on 2022/09/11.
 */
interface ParticipationFormDataDao {

    /**
     * 현재 사용중인 가입 신청서를 가져오는 함수
     */
    fun findUsedParticipationFormByMeetingId(meetingId: Long): ParticipationFormData

    fun findById(id: Long): ParticipationFormData
}