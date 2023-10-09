package com.example.spring2023.extern;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;




@Configuration
public class RabbitConfiguration {
    Logger logger= LoggerFactory.getLogger((RabbitConfiguration.class));

    /*@Bean
    public ConnectionFactory connectionFactory() {
    }*/
}
