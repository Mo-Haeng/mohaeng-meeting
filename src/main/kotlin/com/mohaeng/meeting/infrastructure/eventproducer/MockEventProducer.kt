package com.mohaeng.meeting.infrastructure.eventproducer

import com.mohaeng.meeting.configuration.event.Event
import com.mohaeng.meeting.infrastructure.log.Log
import org.springframework.stereotype.Service

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