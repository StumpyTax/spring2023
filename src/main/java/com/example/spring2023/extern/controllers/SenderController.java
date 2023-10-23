package com.example.spring2023.extern.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {
    @GetMapping
    public void getMapping(){}
}
