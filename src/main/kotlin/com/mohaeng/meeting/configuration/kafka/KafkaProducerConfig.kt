package com.mohaeng.meeting.infrastructure.eventproducer.kafka

import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

/**
 * Created by ShinD on 2022/09/20.
 */
@EnableKafka
@Configuration
class KafkaProducerConfig {

    /**
     * Kafka Producer 지정
     *
     * 키와 벨류 모두 String 형식
     */
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {

        val properties = hashMapOf<String, Any>()

        properties[BOOTSTRAP_SERVERS_CONFIG] = "127.0.0.1:9092"
        properties[KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        properties[VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return DefaultKafkaProducerFactory(properties)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> =
        KafkaTemplate(producerFactory())
}