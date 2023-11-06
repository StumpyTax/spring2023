package com.example.spring2023.extern.Tests;

import com.example.spring2023.app.entity.UserEntity;
import com.example.spring2023.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class UserTests {
    @Test
    public void setEmptyUserPassword(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
                    User user = new User("g", "g", "g");
                    user.setPassword("");
                });
            Assert.assertEquals("Incorrect password",thrown.getMessage());
        }
    @Test
    public void setSpaceUserPassword(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setPassword("            ");
        });
            Assert.assertEquals("Incorrect password",thrown.getMessage());

    }

    @Test
    public void setUserPassword(){
        User user=new User("g","g","g");
        user.setPassword("ggg");
        Assert.assertEquals("ggg",user.getPassword());
    }
    @Test
    public void setEmptyUserName(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setName("");
        });
            Assert.assertEquals("Incorrect name",thrown.getMessage());

    }
    @Test
    public void setSpaceUserName(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setName("            ");
        });
            Assert.assertEquals("Incorrect name",thrown.getMessage());

    }

    @Test
    public void setUserName(){
        User user=new User("g","g","g");
        user.setName("ggg");
        Assert.assertEquals("ggg",user.getName());
    }
    @Test
    public void setEmptyUserLogin(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setLogin("");
        });
            Assert.assertEquals("Incorrect login",thrown.getMessage());

    }
    @Test
    public void setSpaceUserLogin(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setLogin("            ");
        });
            Assert.assertEquals("Incorrect login",thrown.getMessage());

    }

    @Test
    public void setUserLogin(){
        User user=new User("g","g","g");
        user.setLogin("ggg");
        Assert.assertEquals("ggg",user.getLogin());
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
