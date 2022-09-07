package com.footiestats

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = ["com.footiestats"])
class AspectConfig {

    @Aspect
    @Component
    class ControllerLoggingAllAdvice {
        val logger = Logger.getLogger(AspectConfig::class.java.name)

//        @Autowired
//        val rabbitMqProducer = RabbitMqProducer();

        @Before("execution(public * com.footiestats.controller.FootieController.*(..))")
        fun logController(joinPoint: JoinPoint) {
            logger.info("Logging controller  $joinPoint  with no arg ")
         //   rabbitMqProducer.send("Received request for $joinPoint")
        }
    }

}