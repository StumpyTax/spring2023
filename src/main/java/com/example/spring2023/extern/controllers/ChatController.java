package com.example.spring2023.extern.controllers;

import com.example.spring2023.app.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "",produces = "application/json")
public class ChatController {

    @Autowired
    private ChatService service;
}
