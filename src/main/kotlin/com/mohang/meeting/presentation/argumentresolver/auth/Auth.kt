package com.mohang.meeting.presentation.argumentresolver.auth

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER

/**
 * Created by ShinD on 2022/09/08.
 */
@Target(VALUE_PARAMETER)
@Retention(RUNTIME)
annotation class Auth(

    /**
     * 블랙 리스트를 허용할 것인지에 대한 속성,
     * 기본적으로 false이며, 특별한 이유가 없는 이상 블랙리스트에게는 허용하지 않을 것임
     */
    val permitBlacklist: Boolean = false

)