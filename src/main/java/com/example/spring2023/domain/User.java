package com.example.spring2023.domain;

import lombok.Getter;

public class User {
    /**Id пользователя*/
    @Getter
    private int id;
    /**Имя пользователя*/
    @Getter
    private String name;
    /**Логин*/
    @Getter
    private String login;
    /**Пароль*/
    @Getter
    private String password;
    /**Ключ*/
    @Getter
    private String routeKey;

    public User(String name, String login,String password){
        this.name=name;
        this.login=login;
        this.password=password;
    }

    /**Меняет пароль
     * @param newPassword Новый пароль
     */
    public void changePassword(String newPassword){
        password=newPassword;
    }

}
