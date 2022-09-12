package com.mohang.meeting.query.data.applyform

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

/**
 * Created by ShinD on 2022/09/11.
 */
internal class ApplyFormDataTest {


    private val objectMapper = ObjectMapper().registerModule(
        KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.SingletonSupport, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()
    )

    private val JSON = """
        {
            "id":10,
            "createdAt":"2020-00-00 10:00:00",
            "modifiedAt":"2020-00-00 10:00:00",
            "name":"sample form",
            "meetingId":20,
            "fields":[
                {   
                    "id":100,  
                     "name":"sample field 1" 
               },
                {  
                     "id":101,   
                    "name":"sample field 2" 
               }
            ]
        }
    """.trimIndent()

    @Test
    fun `ApplyFormData to Json`() {

        //given
        val applyFormId = 10L
        val createdAt = "2020-00-00 10:00:00" // 가입 신청서 생성일
        val modifiedAt = "2020-00-00 10:00:00" // 가입 신청서 수정일

        val name = "sample form" // 가입 신청서 양식의 이름

        val meetingId = 20L // 해당 가입 신청서 양식을 모임 ID

        val fieldId = 100L
        val fieldName = "sample field 1" // 필드 이름


        val fieldId2 = 101L
        val fieldName2 = "sample field 2" // 필드 이름

        val applyFormData = ApplyFormData(
            applyFormId,
            createdAt,
            modifiedAt,
            name,
            meetingId,
        )
        applyFormData.addAllFields(listOf(
            ApplyFormFieldData(
                id = fieldId,
                name = fieldName
            ),
            ApplyFormFieldData(
                id = fieldId2,
                name = fieldName2
            )
        ))


        //when
        val result = objectMapper.writeValueAsString(applyFormData)

        //then
        expectThat(result.replace(" ", "")) {
            isEqualTo(JSON.replace(" ", "").replace("\n", ""))
        }
    }
}