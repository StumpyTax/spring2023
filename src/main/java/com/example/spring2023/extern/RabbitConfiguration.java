package com.example.spring2023.extern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    Logger logger = LoggerFactory.getLogger((RabbitConfiguration.class));

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue messagesQueue() {
        return new Queue("messagesQueue", true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("messagesExchange", true, false);
    }

    @Bean
    public Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(directExchange()).with("messages");
    }
}
