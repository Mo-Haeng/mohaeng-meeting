package com.mohaeng.meeting.application.usecase.writtenparticipationform

import com.mohaeng.meeting.application.facade.exception.NotMatchParticipationFormException
import com.mohaeng.meeting.application.facade.exception.UnfilledParticipationFormFieldException
import com.mohaeng.meeting.application.usecase.participant.dto.SaveWrittenParticipationFormDto
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.infrastructure.persistence.writtenparticipationform.WrittenParticipationFormRepository
import com.mohaeng.meeting.query.dao.participationform.ParticipationFormDataDao
import com.mohaeng.meeting.query.data.participationform.ParticipationFormData
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/12.
 */
@Service
class SaveWrittenParticipationFormUseCase(

    private val writtenParticipationFormRepository: WrittenParticipationFormRepository,

    private val participationFormDataDao: ParticipationFormDataDao,

    ) {

    @Log
    fun command(
        meetingId: Long,
        saveWrittenFormDto: SaveWrittenParticipationFormDto?,
    ) {

        // 작성해야 할 가입 신청서와, 요청으로 보낸 가입 신청서 검사
        validateWrittenParticipationForm(
            meetingId = meetingId,
            saveWrittenParticipationFormDto = saveWrittenFormDto,
        )

        // 가입 신청서를 받는 모임인 경우 저
        saveWrittenFormDto?.let {
            writtenParticipationFormRepository.save(it.toEntity())
        }
    }

    /**
     * 모임에서 현재 사용중인 가입 신청서를 조회한다.
     * 만약 현재 사용중인 가입신청서가 있는 경우 -> 작성된 가입 신청서의 양식과 비교하여 올바르다면 반환, 올바르지 않거나 없는 경우 예외를 발생시킨다. (작성해야 할 가입 신청서가 새로 생성되었거나 변경되었습니다.)
     */
    private fun validateWrittenParticipationForm(
        meetingId: Long,
        saveWrittenParticipationFormDto: SaveWrittenParticipationFormDto?,
    ) {

        // 모임의 사용중인 가입 신청서 양식 조회
        val participationFormData = participationFormDataDao.findUsedParticipationFormByMeetingId(meetingId)

        // 사용중인 가입 신청서 양식이 존재하지 않는 경우 저장을 시도하지 않고 반환
        if (!participationFormData.isExist) return

        // 작성된 가입 신청서가 없거나, 양식이 일치하지 않는다면 예외
        if (saveWrittenParticipationFormDto == null
            || participationFormData.id != saveWrittenParticipationFormDto.participationFormId
        ) throw NotMatchParticipationFormException()

        // 작성해야할 모든 필드를 작성한 건지 검사
        validateFields(saveWrittenParticipationFormDto, participationFormData)
    }

    /**
     * 작성해야할 모든 필드를 작성한 건지 검사한다
     */
    private fun validateFields(
        writtenForm: SaveWrittenParticipationFormDto,
        participationForm: ParticipationFormData,
    ) {

        // 사이즈가 다른 경우 예외를 발생시킨다.
        if (writtenForm.writtenParticipationFormFields.size != participationForm.fields.size)
            throw UnfilledParticipationFormFieldException()

        // 사이즈가 같은 경우 필요한 필드를 모두 작성했는지 검사한다.
        // id가 같은지 검사 하기 위해서 변환해준다
        val writtenFormFieldIds = writtenForm.writtenParticipationFormFields.map { it.participationFormFieldId }
        val participationFormFieldIds = participationForm.fields.map { it.id }

        // id 가 모두 일치하지 않는다면 오류 (사이즈가 같으므로 containsAll이 true라면 모두 작성된 것)
        if (!writtenFormFieldIds.containsAll(participationFormFieldIds))
            throw UnfilledParticipationFormFieldException()
    }
}