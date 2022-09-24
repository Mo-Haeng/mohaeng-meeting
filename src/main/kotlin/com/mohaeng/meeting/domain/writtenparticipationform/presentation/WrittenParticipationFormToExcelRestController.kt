package com.mohaeng.meeting.domain.writtenparticipationform.presentation

import com.mohaeng.meeting.domain.meeting.presentation.model.AuthMember
import com.mohaeng.meeting.domain.writtenparticipationform.facade.WrittenParticipationFormToExcelFacade
import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.global.web.argumentresolver.auth.Auth
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URLEncoder
import javax.servlet.http.HttpServletResponse
import kotlin.text.Charsets.UTF_8

/**
 * Created by ShinD on 2022/09/18.
 */
@RestController
class WrittenParticipationFormToExcelRestController(

    private val writtenParticipationFormToExcelFacade: WrittenParticipationFormToExcelFacade,

    ) {

    companion object {

        private const val MEDIA_TYPE_EXCEL = "application/vnd.ms-excel"
    }



    @Log
    @GetMapping("/api/participantForm/{participantFormId}/excel")
    fun downloadParticipantFormToExcel(

        @PathVariable(name = "participantFormId") participantFormId: Long,
        @Auth authMember: AuthMember,
        response: HttpServletResponse,
    ) {

        val excelFileName = writtenParticipationFormToExcelFacade.createExcel(
            participantFormId = participantFormId,
            memberId = authMember.id,
            os = response.outputStream,
        )

        response.contentType = MEDIA_TYPE_EXCEL

        response.characterEncoding = UTF_8.name()

        val fileNameUtf8 = URLEncoder.encode(excelFileName, UTF_8.name())

        response.setHeader("Content-Disposition", "attachment; filename=$fileNameUtf8.xlsx")
    }
}