package com.example.spring2023.extern.controllers;

import com.example.spring2023.app.services.MessageService;
import com.example.spring2023.domain.Message;
import com.example.spring2023.extern.response.BaseResponse;
import com.example.spring2023.extern.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/send")
    @ResponseBody
    public BaseResponse send(Long chatId, Long senderId, String messageText, LocalDateTime date) {
        try {
            Message message = messageService.create(senderId, chatId, messageText, date);
            template.setExchange("messagesExchange");
            template.convertAndSend("messages", message);
            return new BaseResponse("", ResponseCode.OK, "OK");
        } catch (Exception e) {
            return new BaseResponse("", ResponseCode.BAD, e.getMessage());
        }
    }
    @RequestMapping("/canChange")
    public BaseResponse can(Long messageId,Long userId){
        try{
            return new BaseResponse(messageService.canChange(messageId,userId).toString(),ResponseCode.OK,"");
        }
        catch (Exception e){
            return  new BaseResponse("",ResponseCode.BAD,e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public BaseResponse delete(Long messageId,Long userId){
        try{
            messageService.delete(messageId, userId);
            return new BaseResponse("",ResponseCode.OK,"Ok");
        }
        catch(Exception e){
            return  new BaseResponse("",ResponseCode.BAD,e.getMessage());
        }
    }
    @PutMapping("change")
    public BaseResponse change(Long messageId, Long userId, String newText,LocalDateTime date){
        try{
            messageService.change(messageId, userId, newText, date);
            return new BaseResponse("",ResponseCode.OK,"Ok");
        }
        catch (Exception e){
            return  new BaseResponse("",ResponseCode.BAD,e.getMessage());
        }
    }
}
