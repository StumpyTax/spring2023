package com.example.spring2023.app;

import com.example.spring2023.domain.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;

public interface IUserService {
    /**
     * Регистрирует нового ползователя.
     *
     * @param login Логин.
     * @param password Пароль.
     * @return Возвращает BaseResponse
     * */
    public BaseResponse Register(String login,String password);
    /**Удаляет пользователя по ID.
     * @param id id Пользователя
     * @return Возвращает BaseResponse
     * */
    public BaseResponse DeleteUser(String id);
    /**
     * Авторизирует пользователя.
     *
     * Параметры:
     *  @param  login  Логин.
     *  @param  password Пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse Logging(String login, String password);
    /**
     * Изменение пароля с подтверждением старого.
     *
     *  @param  id:id пользователя.
     *  @param  oldPassword: текущий пароль.
     *  @param  newPassword: новый пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse ChangePassword(String id,String oldPassword,String newPassword);
    /**
     * Сбрасывает пароль.
     *
     *  @param  id:id пользователя.
     *  @param  newPassword: новый пароль.
     *  @return Возвращает BaseResponse
     * */
    public BaseResponse ResetPassword(String id,String newPassword);
}
