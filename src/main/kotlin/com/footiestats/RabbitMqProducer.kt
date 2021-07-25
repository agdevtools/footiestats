package com.footiestats

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Delivery
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

    val QUEUE_NAME = "hello"
    @Throws(Exception::class)
    fun send(message: String) {
        val factory = ConnectionFactory()
        factory.host = "squid.rmq.cloudamqp.com"
        factory.setUsername("cvofxaso");
        factory.setPassword("bh2MdB29UtcNXf7b2rwEB4gXGbmho9fV");
        factory.newConnection("amqps://cvofxaso:bh2MdB29UtcNXf7b2rwEB4gXGbmho9fV@squid.rmq.cloudamqp.com/cvofxaso").use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(QUEUE_NAME, false, false, false, null)
                channel.basicPublish(
                    "",
                    QUEUE_NAME,
                    null,
                    message.toByteArray(StandardCharsets.UTF_8)
                )
                println(" [x] Sent '$message'")
            }
        }
    }

    fun send2(message : String) {
        var uri = System.getenv("CLOUDAMQP_URL")
        if (uri == null) uri = "amqps://cvofxaso:bh2MdB29UtcNXf7b2rwEB4gXGbmho9fV@squid.rmq.cloudamqp.com/cvofxaso"
        val factory = ConnectionFactory()
        factory.setUri(uri)
        factory.requestedHeartbeat = 30
        factory.connectionTimeout = 30
        val connection: Connection = factory.newConnection()
        val channel: Channel = connection.createChannel()
        channel.queueDeclare("hello", true, false, false, null)
       // val message = "Hello CloudAMQP!"
        channel.basicPublish("", "hello", null, message.toByteArray())
        println(" [x] Sent '$message'")
    }

}