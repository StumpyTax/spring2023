package com.example.spring2023;

import com.example.spring2023.domain.Chat;
import com.example.spring2023.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Tests {


    @Test
    public void setEmptyUserPassword(){
     User user=new User("g","g","g");
     user.changePassword("");
     Assert.assertEquals("g",user.getPassword());
    }

    @Test
    public void setUserPassword(){
        User user=new User("g","g","g");
        user.changePassword("ggg");
        Assert.assertEquals("ggg",user.getPassword());
    }
    @Test
    public void setEmptyChatName(){
        Chat chat=new Chat(new ArrayList<String>(List.of("gg","gg1","gg")),"g","g");
        chat.setName("");
        Assert.assertEquals("g",chat.getName());
    }
    @Test
    public void setSpaceChatName(){
        Chat chat=new Chat(new ArrayList<String>(List.of("gg","gg1","gg")),"g","g");
        chat.setName("     g");
        Assert.assertEquals("g",chat.getName());
    }
}
