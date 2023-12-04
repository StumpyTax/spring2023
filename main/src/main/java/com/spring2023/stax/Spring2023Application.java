package com.spring2023.stax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class Spring2023Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring2023Application.class, args);
    }

}
