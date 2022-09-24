package com.mohaeng.meeting.infrastructure.eventproducer.kafka

import com.mohaeng.meeting.global.aop.log.Log
import com.mohaeng.meeting.global.configuration.event.Event
import com.mohaeng.meeting.global.eventproducer.EventProducer
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/20.
 */
@Service
class KafkaProducer(

    private val kafkaTemplate: KafkaTemplate<String, String>

) : EventProducer {

    private val log = KotlinLogging.logger {  }

    @Log
    override fun send(event: Event, targetId: Long) {

        kafkaTemplate.send(event.event(), targetId.toString())

        log.info { "Kafka topic[${event.event()}](이)가 잘 전달되엇습니다. targetId : $targetId" }

    }
}