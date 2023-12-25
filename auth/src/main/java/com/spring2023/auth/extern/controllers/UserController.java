package com.spring2023.auth.extern.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users", produces = "application/json")
public class UserController {

    @GetMapping("/getUser/{id}")
    public String getUser(Long id){return "netu";}
}