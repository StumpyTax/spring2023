package com.example.spring2023.extern.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class ReceiverController {
    Logger logger = LoggerFactory.getLogger(ReceiverController.class);

    @RabbitListener(queues = "query-example-3-1")
    public void worker1(String message) {
        logger.info("accepted on worker 1 : " + message);
    }

    @RabbitListener(queues = "query-example-3-2")
    public void worker2(String message) {
        logger.info("accepted on worker 2 : " + message);
    }
}
