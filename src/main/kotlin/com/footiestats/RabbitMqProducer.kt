package com.footiestats

import com.rabbitmq.client.ConnectionFactory
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class RabbitMqProducer {

    fun main() {
        val factory = ConnectionFactory()
        factory.newConnection("amqps://qlmyewbx:OBj45osHeb2TD5PEVOewGBoTnml_C1wb@squid.rmq.cloudamqp.com/qlmyewbx").use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare("footie-stats-test-queue", false, false, false, null)
                val message = "Hello World!"
                channel.basicPublish(
                    "",
                    "footie-stats-test-queue",
                    null,
                    message.toByteArray(StandardCharsets.UTF_8)
                )
                println(" [x] Sent '$message'")
            }
        }
    }

}