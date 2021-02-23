package com.footiestats.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService {

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    private val logger: Logger = LoggerFactory.getLogger(com.footiestats.service.KafkaService::class.java)
    private val TOPIC = "footiestats"

    fun sendMessage(message: String?) {
        logger.info(String.format("$$$ -> Producing message --> %s", message))
        kafkaTemplate?.send(TOPIC, message)
    }

}