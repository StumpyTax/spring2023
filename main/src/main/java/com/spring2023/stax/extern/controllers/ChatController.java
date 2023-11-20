package com.spring2023.stax.extern.controllers;

import com.spring2023.stax.app.services.ChatService;
import com.spring2023.stax.extern.response.BaseResponse;
import com.spring2023.stax.extern.response.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chats",produces = "application/json")
public class ChatController {

    @Autowired
    private ChatService service;
    @Autowired
    private ObjectMapper objectMapper;


    /**Получение чата по ID
     * @param id чата
     * @return
     */
    @GetMapping("/{id}")
    public BaseResponse get(@PathVariable Long id){
        try {
            return new BaseResponse(objectMapper.writeValueAsString(service.get(id)),ResponseCode.OK,"");
        }
        catch (Exception e){
            return new BaseResponse("", ResponseCode.BAD,e.getMessage());
        }
    }

    /**Создает чат
     * @param name имя чата
     * @param ownerId ID создателя чата
     * @param usersIds ID пользователей чата
     * @return BaseResponse
     */
    @PostMapping("/create")
    public BaseResponse create(@RequestParam String name,
                               @RequestParam Long ownerId,
                               @RequestParam List<Long> usersIds){
        try {
            return new BaseResponse(objectMapper.writeValueAsString(service.create(name,ownerId,usersIds)),
                    ResponseCode.OK,"");
        }
        catch (Exception e){
            return new BaseResponse("", ResponseCode.BAD,e.getMessage());
        }
    }

    /**
     * Добавляет пользователей в чат
     *
     * @param chatId id чата
     * @param userIds id пользователей
     * @return BaseResponse
     */
    @PutMapping("/addUsers")
    public BaseResponse addUsers(@RequestParam Long chatId,@RequestParam List<Long> userIds){
        try{
            service.addUsers(chatId,userIds);
            return new BaseResponse("",ResponseCode.OK,"Ok");
        }
        catch (Exception e){
            return new BaseResponse("",ResponseCode.BAD,e.getMessage());
        }
    }

    /**
     * Удаляет пользователя из чата
     *
     * @param chatId id чата
     * @param userId id пользователя
     * @return BaseResponse
     */
    @PutMapping("/deleteUsers")
    public BaseResponse deleteUsers(@RequestParam Long chatId,@RequestParam Long userId ){
        try{
            service.deleteUser(chatId,userId);
            return new BaseResponse("",ResponseCode.OK,"Ok");
        }
        catch (Exception e) {
            return new BaseResponse("", ResponseCode.BAD, e.getMessage());
        }
    }
    public BaseResponse changeName(@RequestParam Long chatId,@RequestParam String newName ){
        try{
           return new BaseResponse(service.changeName(chatId,newName),ResponseCode.OK,"Ok");
        }
        catch (Exception e){
            return new BaseResponse("", ResponseCode.BAD, e.getMessage());
        }
    }
}
