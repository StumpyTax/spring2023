package com.example.spring2023.Tests;

import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTests {
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
    public  void userToUserEntity(){
        User user=new User(1l,"g","g","g");
        UserEntity entity=new UserEntity(user);
        Assert.assertTrue(user.getId()==entity.getId()
                && user.getLogin().equals(entity.getLogin())
                && user.getName().equals(entity.getName())
                && user.getPassword().equals(entity.getPassword()));
    }
    @Test
    public  void userEntityToUser(){
        UserEntity entity=new UserEntity(1l,"g","g","g");
        User user=entity.toUser();
        Assert.assertTrue(user.getId()==entity.getId()
                && user.getLogin().equals(entity.getLogin())
                && user.getName().equals(entity.getName())
                && user.getPassword().equals(entity.getPassword()));
    }
}
