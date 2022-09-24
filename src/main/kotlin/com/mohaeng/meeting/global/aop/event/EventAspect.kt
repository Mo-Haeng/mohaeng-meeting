package com.mohaeng.meeting.global.aop.event

import com.mohaeng.meeting.global.eventproducer.EventProducer
import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

/**
 * Created by ShinD on 2022/09/25.
 */
@Component
@Aspect
class EventAspect(
    private val eventProducer: EventProducer,
) {

    @AfterReturning("@annotation(com.mohaeng.meeting.global.aop.event.ProduceEvent)", returning = "targetId")
    fun produceEvent(joinPoint: JoinPoint, targetId: Long) {

        val methodSignature = joinPoint.signature as MethodSignature

        val method = methodSignature.method

        val annotation = method.getAnnotation(ProduceEvent::class.java)

        eventProducer.send(annotation.event, targetId)
    }
}