package com.mohaeng.meeting.infrastructure.eventproducer

import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.global.configuration.event.Event
import com.mohaeng.meeting.global.eventproducer.EventProducer

/**
 * Created by ShinD on 2022/09/07.
 */
//@Service
class MockEventProducer : EventProducer {

    @Log
    override fun send(event: Event, targetId: Long) {
        println("${targetId}를 대상으로 ${event} 발생!!")
    }
}