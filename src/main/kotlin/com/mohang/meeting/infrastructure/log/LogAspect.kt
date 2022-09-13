package com.mohang.meeting.infrastructure.log

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

/**
 * Created by ShinD on 2022/09/13.
 */
@Component
@Aspect
class LogAspect {

    private val log = KotlinLogging.logger { }

    @Around("@annotation(com.mohang.meeting.infrastructure.log.Log)")
    fun doLog4(joinPoint: ProceedingJoinPoint): Any? {

        log.debug { "[CALL] : [${joinPoint.signature}]" }

        try {
            return joinPoint.proceed()
        }
        catch (e: Exception) {
            log.debug {
                """
                [METHOD] : [${joinPoint.signature}],
                [EXCEPTION] : [${e::class.java.name}],
                [MESSAGE] : [${e.message}],
                
                 """
            }
            throw e
        }
    }
}