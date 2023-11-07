package com.example.spring2023.extern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class Receiver {
    Logger logger = LoggerFactory.getLogger(Receiver.class);
    @Value("${spring.rabbitmq.queues.messages.name}")
    String messagesQueueName;

    @RabbitListener(queues ="${messageName}")
    public void worker(String message) {
        logger.info("accepted on worker: " + message);
    }

}
