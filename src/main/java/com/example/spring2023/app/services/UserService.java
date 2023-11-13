package com.example.spring2023.app.services;

import com.example.spring2023.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    /**
     * Регистрирует нового ползователя.
     *
     * @param login    Логин.
     * @param password Пароль.
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public void register(String login, String password) throws RuntimeException {

    }

    /**
     * Удаляет пользователя по ID.
     *
     * @param id id Пользователя
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public void delete(String id) throws RuntimeException {

    }

    /**
     * Авторизирует пользователя.
     * <p>
     * Параметры:
     *
     * @param login    Логин.
     * @param password Пароль.
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public void logging(String login, String password) throws RuntimeException {
    }

    /**
     * Изменение пароля с подтверждением старого.
     *
     * @param id          id пользователя.
     * @param oldPassword текущий пароль.
     * @param newPassword новый пароль.
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     *
     */
    public void changePassword(String id, String oldPassword, String newPassword) throws RuntimeException {
    }

    /**
     * Сбрасывает пароль.
     *
     * @param id          id пользователя.
     * @param newPassword новый пароль.
     * @return Возвращает BaseResponse
     * @throws RuntimeException
     */
    public void resetPassword(String id, String newPassword) throws RuntimeException {

    }
}
