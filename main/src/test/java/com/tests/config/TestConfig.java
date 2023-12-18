package com.tests.config;

import com.spring2023.stax.app.services.ChatService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ChatService chatService(){
        return Mockito.mock(ChatService.class);
    }

}
