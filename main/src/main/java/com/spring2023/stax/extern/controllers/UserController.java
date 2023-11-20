package com.spring2023.stax.extern.controllers;

import com.spring2023.stax.app.entity.UserEntity;
import com.spring2023.stax.app.repositories.UserRepository;
import com.spring2023.stax.domain.User;
import com.spring2023.stax.extern.response.BaseResponse;
import com.spring2023.stax.extern.response.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users",produces = "application/json")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public  BaseResponse getUser(@PathVariable long id){
        return new BaseResponse("gg",ResponseCode.OK,"gg");
    }
    /**Создает тестового пользователя
     * @return BaseResponse
     */
    @PostMapping("/createTestUser")
    public BaseResponse createTestUser(){
        User user=new User("Ya_rodilsya","GG","Luntik");
        repository.save(new UserEntity(user));
        return  new BaseResponse(""+user.getId(), ResponseCode.OK,"");
    }
}
