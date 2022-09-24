package com.mohaeng.meeting.global.aop.event

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * Created by ShinD on 2022/09/25.
 */
@Target(FUNCTION)
@Retention(RUNTIME)
annotation class ProduceEvent(

    val event: String,

)
