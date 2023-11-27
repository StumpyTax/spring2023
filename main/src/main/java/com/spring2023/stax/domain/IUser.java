package com.spring2023.stax.domain;

public interface IUser {
    /**Меняет пароль
     * @param newPassword Новый пароль
     */
    public void setPassword(String newPassword) throws RuntimeException;

    /**
     * Изменяет имя
     *
     * @param newName Новое имя
     * @throws RuntimeException
     */
    public void setName(String newName) throws RuntimeException;

    /**
     * Изменяет логин
     *
     * @param newLogin Новый логин
     * @throws RuntimeException
     */
    public void setLogin(String newLogin) throws RuntimeException;
}
