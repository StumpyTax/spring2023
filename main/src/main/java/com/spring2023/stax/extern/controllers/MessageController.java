package com.spring2023.stax.extern.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2023.stax.app.entity.MessageEntity;
import com.spring2023.stax.app.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/messages", produces = "application/json")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    RabbitTemplate template;
    @Autowired
    MessageService messageService;
    @Value("${spring.rabbitmq.exchanges.messages.name}")
    String messagesExchangeName;
    @Value("${spring.rabbitmq.bindings.routingKey}")
    String routingKey;
    @Autowired
    ObjectMapper objectMapper=new ObjectMapper();

    @RequestMapping("/send")
    @ResponseBody
    public ResponseEntity<String> send(Long chatId, Long senderId, String messageText, LocalDateTime date) {
        try {
            MessageEntity message = (MessageEntity) messageService.create(senderId, chatId, messageText, date);
            template.setExchange(messagesExchangeName);
            template.convertAndSend(routingKey, message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @RequestMapping("/canChange")
    public ResponseEntity<String> can(Long messageId,Long userId){
        try{
            return ResponseEntity.ok(objectMapper.writeValueAsString(messageService.canChange(messageId,userId)));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(Long messageId,Long userId){
        try{
            messageService.delete(messageId, userId);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PutMapping("change")
    public ResponseEntity<String> change(Long messageId, Long userId, String newText,LocalDateTime date){
        try{
            messageService.change(messageId, userId, newText, date);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
