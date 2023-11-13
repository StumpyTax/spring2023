package com.tests;

import com.example.spring2023.domain.Chat;
import com.example.spring2023.domain.Message;
import com.example.spring2023.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MessagesTests {

    @Test
    public void setEmptyText(){

        RuntimeException thrown=Assertions.assertThrows(RuntimeException.class, ()->{
            Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
            message.setText("");});
        Assertions.assertEquals("Incorrect text", thrown.getMessage());
    }
    @Test
    public void setSpaceText(){
        RuntimeException thrown=Assertions.assertThrows(RuntimeException.class, ()->{
            Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
            message.setText("           ");});
        Assertions.assertEquals("Incorrect text", thrown.getMessage());
    }

    @Test
    public void setUserPassword(){
        Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
        message.setText("ggg");
        Assertions.assertEquals("ggg", message.getText());
    }

    @Test
    public  void setLastChangeBefore(){
        RuntimeException thrown=Assertions.assertThrows(RuntimeException.class, ()->{
            Message message = new Message("adsf", LocalDateTime.now(), new User(), new Chat());
            message.setLastChange(LocalDateTime.now().minusMonths(9));});
        Assertions.assertEquals("Incorrect date", thrown.getMessage());
    }

    @Test
    public  void setLastChange(){
            Message message = new Message("adsf", LocalDateTime.now(), new User(), new Chat());
            LocalDateTime date=LocalDateTime.now().plusMonths(9);
            message.setLastChange(date);
            Assertions.assertEquals(date.toString(), message.getLastChange().toString());
    }

}
