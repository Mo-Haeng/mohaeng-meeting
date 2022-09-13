package com.mohang.meeting.infrastructure.eventproducer

import com.mohang.meeting.infrastructure.log.Log
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/07.
 */
@Service
class MockEventProducer : EventProducer {

    @Log
    override fun send(event: String, targetId: Long) {
        println("${targetId}를 대상으로 ${event} 발생!!")
    }
}