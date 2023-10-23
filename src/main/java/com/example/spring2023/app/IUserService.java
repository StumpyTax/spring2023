package com.example.spring2023.app;

import com.example.spring2023.extern.response.BaseResponse;

public interface IUserService {
    /**
     * Регистрирует нового ползователя.
     *
     * @param login Логин.
     * @param password Пароль.
     * @return Возвращает BaseResponse
     * */
    public BaseResponse register(String login,String password);
    /**Удаляет пользователя по ID.
     * @param id id Пользователя
     * @return Возвращает BaseResponse
     * */
    public BaseResponse deleteUser(String id);
    /**
     * Авторизирует пользователя.
     *
     * Параметры:
     *  @param  login  Логин.
     *  @param  password Пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse logging(String login, String password);
    /**
     * Изменение пароля с подтверждением старого.
     *
     *  @param  id:id пользователя.
     *  @param  oldPassword: текущий пароль.
     *  @param  newPassword: новый пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse changePassword(String id,String oldPassword,String newPassword);
    /**
     * Сбрасывает пароль.
     *
     *  @param  id:id пользователя.
     *  @param  newPassword: новый пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse resetPassword(String id,String newPassword);
}
