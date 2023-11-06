package com.example.spring2023.extern.Tests;

import com.example.spring2023.domain.Chat;
import com.example.spring2023.domain.Message;
import com.example.spring2023.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class MessagesTests {

    @Test
    public void setEmptyText(){
        try {
            Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
            message.setText("");
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Incorrect text");
        }
    }
    @Test
    public void setSpaceText(){
        try {
            Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
            message.setText("           ");
        }
        catch (Exception e){
            Assert.assertEquals(e.getMessage(),"Incorrect text");
        }
    }

    @Test
    public void setUserPassword(){
        Message message = new Message("adsf", LocalDateTime.now(),new User(),new Chat());
        message.setText("ggg");
        Assert.assertEquals("ggg",message.getText());
    }

    @Test
    public  void setLastChangeBefore(){
        try {
            Message message = new Message("adsf", LocalDateTime.now(), new User(), new Chat());
            message.setLastChange(LocalDateTime.now().minusMonths(9));
        }
        catch (Exception e){
            Assert.assertEquals("Incorrect date",e.getMessage());
        }
    }

    @Test
    public  void setLastChange(){
            Message message = new Message("adsf", LocalDateTime.now(), new User(), new Chat());
            LocalDateTime date=LocalDateTime.now().plusMonths(9);
            message.setLastChange(date);
            Assert.assertEquals(date.toString(),message.getLastChange().toString());
    }

}
