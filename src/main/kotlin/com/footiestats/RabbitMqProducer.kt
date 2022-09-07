//package com.footiestats
//
//import com.rabbitmq.client.Channel
//import com.rabbitmq.client.Connection
//import com.rabbitmq.client.ConnectionFactory
//import org.springframework.stereotype.Service
//import java.util.logging.Logger
//
//
//@Service
//class RabbitMqProducer {
//
//    fun send(message : String) {
//        val logger = Logger.getLogger(RabbitMqProducer::class.java.name)
//        var uri = System.getenv("CLOUDAMQP_URL")
//
//        if (uri == null) uri = "amqps://cvofxaso:bh2MdB29UtcNXf7b2rwEB4gXGbmho9fV@squid.rmq.cloudamqp.com/cvofxaso"
//        val factory = ConnectionFactory()
//
//        factory.setUri(uri)
//        factory.requestedHeartbeat = 30
//        factory.connectionTimeout = 30
//        val connection: Connection = factory.newConnection()
//        val channel: Channel = connection.createChannel()
//        channel.queueDeclare("hello", true, false, false, null)
//        channel.basicPublish("", "hello", null, message.toByteArray())
//
//        logger.info(" [x] Sent '$message'")
//    }
//
//}