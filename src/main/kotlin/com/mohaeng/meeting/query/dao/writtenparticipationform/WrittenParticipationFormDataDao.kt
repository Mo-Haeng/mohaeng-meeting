package com.mohaeng.meeting.query.dao.writtenparticipationform

import com.mohaeng.meeting.query.data.writtenparticipationform.WrittenParticipationFormData

/**
 * Created by ShinD on 2022/09/16.
 */
interface WrittenParticipationFormDataDao {

    /**
     * 어떠한 가입신청서 양식에 해당하는 작성된 신청서들 모두 조회
     */
    fun getAllByParticipationFormId(participationFormId: Long): List<WrittenParticipationFormData>
}