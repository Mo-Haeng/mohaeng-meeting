package com.mohaeng.meeting.global.eventproducer

/**
 * Created by ShinD on 2022/09/07.
 */
interface EventProducer {

    // 어떤 id를 대상으로 어떤 이벤트가 발생하였나
    fun send(event: String, targetId: Long)
}