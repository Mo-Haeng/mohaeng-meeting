package com.mohaeng.meeting.query.dao.applyform

import com.mohaeng.meeting.query.data.applyform.ApplyFormData

/**
 * Created by ShinD on 2022/09/11.
 */
interface ApplyFormDataDao {

    /**
     * 현재 사용중인 가입 신청서를 가져오는 함수
     */
    fun findUsedApplyFormByMeetingId(meetingId: Long): ApplyFormData
}