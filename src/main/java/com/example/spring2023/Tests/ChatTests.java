package com.example.spring2023.Tests;

import com.example.spring2023.domain.Chat;
import com.example.spring2023.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatTests {

    @Test
    public void setEmptyChatName(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User("gg","gfd","gfda"))),
                "g",new User("fds","asdf","fsadf"));
        chat.setName("");
        Assert.assertEquals("g",chat.getName());
    }
    @Test
    public void setSpaceChatName(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User("gg","gfd","gfda"))),
                "g",new User("fds","asdf","fsadf"));
        chat.setName("     g");
        Assert.assertEquals("g",chat.getName());
    }
    @Test
    public void addEmptyUsers(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        chat.addUsers(Collections.emptyList());
        Assert.assertEquals(List.of(1l),chat.getMembers().stream().map(User::getId).toList());
    }
    @Test
    public void addSomeUsers(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        chat.addUsers(new ArrayList<User>(List.of(new User(3l,"gg","gfd","gfda"),
         new User(4l,"fds","asdf","fsadf"))));
        Assert.assertEquals(List.of(1l,3l,4l),chat.getMembers().stream().map(User::getId).toList());
    }
    @Test
    public void notInUsers(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        Assert.assertFalse(chat.inUsers(new User(3l, "fds", "asdf", "fsadf")));
    }
    @Test
    public void InUsers(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        Assert.assertFalse(chat.inUsers(new User(2l, "fds", "asdf", "fsadf")));
    }
    @Test
    public void deleteUserNotInList(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"),
                new User(3l,"fds","asdf","fsadf"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        chat.deleteUser(2l);
        Assert.assertEquals(List.of(1l,3l),chat.getMembers().stream().map(User::getId).toList());
    }
    @Test
    public void deleteUser(){
        Chat chat=new Chat(1l,new ArrayList<User>(List.of(new User(1l,"gg","gfd","gfda"),
                new User(3l,"fds","asdf","fsadf"))),
                "g",new User(2l,"fds","asdf","fsadf"));
        chat.deleteUser(1l);
        Assert.assertEquals(List.of(3l),chat.getMembers().stream().map(User::getId).toList());
    }
}
