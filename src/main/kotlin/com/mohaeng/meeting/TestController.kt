package com.mohaeng.meeting

import com.mohaeng.meeting.application.facade.WrittenParticipationFormToExcelFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes.MediaType
import java.net.URLEncoder
import javax.servlet.http.HttpServletResponse

/**
 * Created by ShinD on 2022/09/16.
 */
@RestController
class TestController(
    private val writtenParticipationFormToExcelFacade: WrittenParticipationFormToExcelFacade,
) {

    @GetMapping("/down")
    fun test(response: HttpServletResponse) {

        response.contentType = "application/vnd.ms-excel";

        response.characterEncoding = "utf-8";

        val fileNameUtf8 = URLEncoder.encode("FAST_EXCEL", "UTF-8");

        response.setHeader("Content-Disposition", "attachment; filename=$fileNameUtf8.xlsx");

        writtenParticipationFormToExcelFacade.createExcel(
            1L,
            response.outputStream
        )
    }
}