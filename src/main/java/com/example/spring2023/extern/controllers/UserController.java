package com.example.spring2023.extern.controllers;

import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.app.repositories.UserRepository;
import com.example.spring2023.extern.response.BaseResponse;
import com.example.spring2023.extern.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "",produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository repository;
    /* private ChatService service;*/


    /**Создает тестового пользователя
     * @return BaseResponse
     */
    @RequestMapping("createTestUser")
    public BaseResponse createTestUser(){
        UserEntity user=new UserEntity("Ya_rodilsya","GG","Luntik");
        repository.save(user);
        return  new BaseResponse(""+user.getId(), ResponseCode.OK,"");
    }
}
