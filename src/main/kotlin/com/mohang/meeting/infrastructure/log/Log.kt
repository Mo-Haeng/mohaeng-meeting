package com.mohang.meeting.infrastructure.log

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

/**
 * Created by ShinD on 2022/09/13.
 */
@Target(FUNCTION, CLASS, TYPE)
@Retention(RUNTIME)
annotation class Log
