package com.footiestats

import com.rabbitmq.client.ConnectionFactory
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets


@Service
class RabbitMqProducer {

    fun sendMessage(message : String) {
        val factory = ConnectionFactory()
        factory.newConnection("amqps://cvofxaso:bh2MdB29UtcNXf7b2rwEB4gXGbmho9fV@squid.rmq.cloudamqp.com/cvofxaso").use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare("footie-stats-test-queue", false, false, false, null)
                channel.basicPublish("", "footie-stats-test-queue", null, message.toByteArray(StandardCharsets.UTF_8))
               // println(" [x] Sent '$message'")
            }
        }
    }

}