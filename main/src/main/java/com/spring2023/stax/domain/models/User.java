package com.spring2023.stax.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class User {
    /**Id пользователя*/
    @Getter
    private Long id;
    /**Имя пользователя*/
    @Getter
    private String name;
    /**Логин*/
    @Getter
    private String login;
    /**Пароль*/
    @Getter
    private String password;


}
