package com.tests;

import com.spring2023.stax.app.entity.ChatEntity;
import com.spring2023.stax.app.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatTests {

    @Test
    public void setEmptyChatName(){
        UserEntity mock= Mockito.mock(UserEntity.class);
        RuntimeException thrown=assertThrows(RuntimeException.class,()->{
            ChatEntity chat=new ChatEntity("g",new ArrayList<UserEntity>(List.of(
                    mock)),
               mock);
            chat.setName("");});

        assertEquals("Incorrect name",thrown.getMessage());
    }
    @Test
    public void setSpaceChatName(){
        ChatEntity chat=new ChatEntity("gg",new ArrayList<UserEntity>(List.of(
                new UserEntity("gg","gfd","gfda"))),
                new UserEntity("fds","asdf","fsadf"));
        chat.setName("     g");
        assertEquals("g",chat.getName());
    }
    @Test
    public void addEmptyUsers(){
        ChatEntity chat=new ChatEntity("gg",new ArrayList<UserEntity>(List.of(
                new UserEntity("gg","gfd","gfda"))),
                new UserEntity("fds","asdf","fsadf"));
        chat.addUsers(Collections.emptyList());
        assertEquals(List.of(1l),chat.getUsers().stream().map(UserEntity::getId).toList());
    }
    @Test
    public void addSomeUsers(){
        ChatEntity chat=new ChatEntity("gg",new ArrayList<UserEntity>(List.of(
                new UserEntity(1L,"gg","gfd","gfda"))),
                new UserEntity(2L,"fds","asdf","fsadf"));
        chat.addUsers(new ArrayList<UserEntity>(List.of(new UserEntity(3l,"gg","gfd","gfda"),
         new UserEntity(4l,"fds","asdf","fsadf"))));
        assertEquals(List.of(1l,3l,4l),chat.getUsers().stream().map(UserEntity::getId).toList());
    }
    @Test
    public void notInUsers(){
        ChatEntity chat=new ChatEntity("gg",new ArrayList<UserEntity>(List.of(
                new UserEntity(1L,"gg","gfd","gfda"))),
                new UserEntity(2L,"fds","asdf","fsadf"));
        assertFalse(chat.inUsers(new UserEntity(3l, "fds", "asdf", "fsadf")));
    }
    @Test
    public void InUsers(){
        ChatEntity chat=new ChatEntity("gg",new ArrayList<UserEntity>(List.of(
                new UserEntity(1L,"gg","gfd","gfda"))),
                new UserEntity(2L,"fds","asdf","fsadf"));
        assertFalse(chat.inUsers(new UserEntity(2l, "fds", "asdf", "fsadf")));
    }
    @Test
    public void deleteUserNotInList(){
        ChatEntity chat=new ChatEntity("g",new ArrayList<UserEntity>(List.of(
                new UserEntity(1l,"gg","gfd","gfda"),
                new UserEntity(3l,"fds","asdf","fsadf"))),
                new UserEntity(2l,"fds","asdf","fsadf"));
        chat.deleteUser(2l);
       assertEquals(List.of(1l,3l),chat.getUsers().stream().map(UserEntity::getId).toList());
    }
    @Test
    public void deleteUser(){
        ChatEntity chat=new ChatEntity("g",new ArrayList<UserEntity>(List.of(
                new UserEntity(1l,"gg","gfd","gfda"),
                new UserEntity(3l,"fds","asdf","fsadf"))),
                new UserEntity(2l,"fds","asdf","fsadf"));
        chat.deleteUser(1l);
       assertEquals(List.of(3l),chat.getUsers().stream().map(UserEntity::getId).toList());
    }
    @Test
    public void deleteOwner(){
        List<UserEntity> users=new ArrayList<UserEntity>();
        UserEntity owner=new UserEntity(1L,"dsf","a","asdsa");
        UserEntity newOwner=new UserEntity(2L,"a","dsf","adsf");
        users.add(newOwner);
        ChatEntity chat=new ChatEntity("a",users,owner);
        chat.deleteOwner();
        assertEquals(chat.getOwner().getId(),2L);
    }
}
