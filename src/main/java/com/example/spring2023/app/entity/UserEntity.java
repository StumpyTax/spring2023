package com.example.spring2023.app.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name="Id",nullable = false)
    @Getter
    private long id;
    @Column(name="login",nullable = false)
    @Getter
    private String login;
    @Getter
    @Column(name="password",nullable = false)
    private  String password;
    @Getter
    @Column(name="name",nullable = false)
    private  String name;

    public UserEntity() {
    }
    public UserEntity(String login,String password,String name){
        this.login=login;
        this.password=password;
        this.name=name;
    }


/*    public void setId(long id) {
        this.id = id;
    }*/

/*    public void setLogin(String login) {
        this.login = login;
    }*/


/*    public void setPassword(String password) {
        this.password = password;
    }*/


/*    public void setName(String name) {
        this.name = name;
    }*/
}
