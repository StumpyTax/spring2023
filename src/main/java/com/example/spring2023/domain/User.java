package com.example.spring2023.domain;

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

    public User(String name, String login,String password) throws RuntimeException{
        this.name=name;
        this.login=login;
        this.password=password;
    }
    public User(Long id,String name,String login,String password) throws RuntimeException{
        this.id=id;
        setName(name);
        setLogin(login);
        setPassword(password);
    }

    /**Меняет пароль
     * @param newPassword Новый пароль
     */
    public void setPassword(String newPassword) throws RuntimeException{
        if(!(newPassword.contains(" ") || newPassword.isEmpty()))
            password=newPassword;
        else
            throw new RuntimeException("Incorrect password");
    }

    /**
     * Изменяет имя
     *
     * @param newName Новое имя
     * @throws RuntimeException
     */
    public void setName(String newName) throws RuntimeException {
        if (!newName.replaceAll("\\s+", "").isEmpty())
            name = newName.strip();
        else
            throw new RuntimeException("Incorrect name");
    }

    /**
     * Изменяет логин
     *
     * @param newLogin Новый логин
     * @throws RuntimeException
     */
    public void setLogin(String newLogin) throws RuntimeException {
        if (newLogin.replaceAll("\\w", "").isEmpty() && !newLogin.isEmpty())
            login = newLogin.strip();
        else
            throw new RuntimeException("Incorrect login");
    }
}
