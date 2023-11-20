package com.spring2023.auth.extern.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MyController {

    @GetMapping("/admin")
    public String homeAdmin(Principal principal) {
        return "Hello mr. " + principal.getName();
    }

    @GetMapping("/user")
    public String homeUser(Principal principal) {
        return "Hello mr. " + principal.getName();
    }
}