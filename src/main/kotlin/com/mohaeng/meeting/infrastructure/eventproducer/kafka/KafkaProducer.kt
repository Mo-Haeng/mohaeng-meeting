package com.mohaeng.meeting.infrastructure.eventproducer.kafka

import com.mohaeng.meeting.configuration.event.Event
import com.mohaeng.meeting.infrastructure.eventproducer.EventProducer
import com.mohaeng.meeting.infrastructure.log.Log
import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * Created by ShinD on 2022/09/20.
 */
@Service
class KafkaProducer(

    private val kafkaTemplate: KafkaTemplate<String, String>

) : EventProducer{

    private val log = KotlinLogging.logger {  }

    @Log
    override fun send(event: Event, targetId: Long) {

        kafkaTemplate.send(event.event(), targetId.toString())

        log.info { "Kafka topic[${event.event()}](이)가 잘 전달되엇습니다. targetId : $targetId" }

    }
}