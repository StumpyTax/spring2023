package com.tests;

import com.spring2023.stax.app.entity.UserEntity;
import com.spring2023.stax.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {
    @Test
    public void setEmptyUserPassword(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
                    User user = new User("g", "g", "g");
                    user.setPassword("");
                });
            Assertions.assertEquals("Incorrect password", thrown.getMessage());
        }
    @Test
    public void setSpaceUserPassword(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setPassword("            ");
        });
            Assertions.assertEquals("Incorrect password", thrown.getMessage());

    }

    @Test
    public void setUserPassword(){
        User user=new User("g","g","g");
        user.setPassword("ggg");
        Assertions.assertEquals("ggg", user.getPassword());
    }
    @Test
    public void setEmptyUserName(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setName("");
        });
            Assertions.assertEquals("Incorrect name", thrown.getMessage());

    }
    @Test
    public void setSpaceUserName(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setName("            ");
        });
            Assertions.assertEquals("Incorrect name", thrown.getMessage());

    }

    @Test
    public void setUserName(){
        User user=new User("g","g","g");
        user.setName("ggg");
        Assertions.assertEquals("ggg", user.getName());
    }
    @Test
    public void setEmptyUserLogin(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setLogin("");
        });
            Assertions.assertEquals("Incorrect login", thrown.getMessage());

    }
    @Test
    public void setSpaceUserLogin(){
        RuntimeException thrown=Assert.assertThrows(RuntimeException.class,()-> {
            User user = new User("g", "g", "g");
            user.setLogin("            ");
        });
            Assertions.assertEquals("Incorrect login", thrown.getMessage());

    }

    @Test
    public void setUserLogin(){
        User user=new User("g","g","g");
        user.setLogin("ggg");
        Assertions.assertEquals("ggg", user.getLogin());
    }
    @Test
    public  void userToUserEntity(){
        User user=new User(1L,"g","g","g");
        UserEntity entity=new UserEntity(user);
        Assertions.assertTrue(user.getId()==entity.getId()
                && user.getLogin().equals(entity.getLogin())
                && user.getName().equals(entity.getName())
                && user.getPassword().equals(entity.getPassword()));
    }
    @Test
    public  void userEntityToUser(){
        UserEntity entity=new UserEntity(new User(1L,"g","g","g"));
        User user=entity.toUser();
        Assertions.assertTrue(user.getId()==entity.getId()
                && user.getLogin().equals(entity.getLogin())
                && user.getName().equals(entity.getName())
                && user.getPassword().equals(entity.getPassword()));
    }
}
