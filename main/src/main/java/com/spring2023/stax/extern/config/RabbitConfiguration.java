package com.spring2023.stax.extern.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${spring.rabbitmq.address}")
    public String address;
    @Value("${spring.rabbitmq.queues.messages.name}")
    public String messagesQueueName;
    @Value("${spring.rabbitmq.queues.messages.durable}")
    public Boolean messagesQueueDurable;
    @Value("${spring.rabbitmq.exchanges.messages.name}")
    public String messagesExchangeName;
    @Value("${spring.rabbitmq.exchanges.messages.durable}")
    public Boolean messagesExchangeDurable;
    @Value("${spring.rabbitmq.exchanges.messages.autoDelete}")
    public Boolean messagesExchangeDelete;
    @Value("${spring.rabbitmq.bindings.routingKey}")
    public String routingKey;
    Logger logger = LoggerFactory.getLogger((RabbitConfiguration.class));

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(address);
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
        return new Queue(messagesQueueName, messagesQueueDurable);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(messagesExchangeName, messagesExchangeDurable, messagesExchangeDelete);
    }

    @Bean
    public Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(directExchange()).with(routingKey);
    }
}
