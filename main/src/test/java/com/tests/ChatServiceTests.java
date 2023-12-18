package com.tests;

import com.spring2023.stax.app.services.ChatService;
import com.tests.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestConfig.class)
public class ChatServiceTests {

    @Autowired
    private ChatService chatService;
    private Long testId;

    @Test
    public void getChat(){
        Mockito.when(chatService.get(testId)).thenReturn(null);

    }
}
