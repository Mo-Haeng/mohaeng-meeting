package com.mohaeng.meeting.presentation

import com.mohaeng.meeting.application.facade.WrittenParticipationFormToExcelFacade
import com.mohaeng.meeting.infrastructure.log.Log
import com.mohaeng.meeting.presentation.argumentresolver.auth.Auth
import com.mohaeng.meeting.presentation.model.AuthMember
import org.bouncycastle.util.encoders.UTF8
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