package com.example.spring2023.extern.controllers;

import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.app.repositories.UserRepository;
import com.example.spring2023.extern.response.BaseResponse;
import com.example.spring2023.extern.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users",produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository repository;
    /* private ChatService service;*/

    @GetMapping("/{id}")
    public  BaseResponse getUser(@PathVariable long id){
        return new BaseResponse("gg",ResponseCode.OK,"gg");
    }
    /**Создает тестового пользователя
     * @return BaseResponse
     */
    @PostMapping("/createTestUser")
    public BaseResponse createTestUser(){
        UserEntity user=new UserEntity("Ya_rodilsya","GG","Luntik");
        repository.save(user);
        return  new BaseResponse(""+user.getId(), ResponseCode.OK,"");
    }
}
