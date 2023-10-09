package com.example.spring2023.app;

import com.example.spring2023.domain.Chat;
import com.example.spring2023.domain.Message;
import com.example.spring2023.domain.User;
import com.example.spring2023.domain.response.BaseResponse;

import java.util.Date;

public interface IMessageService {
    /**Создает сообщение
     * @param sender Отправитель сообщения
     * @param routKey Место назначения сообщения
     * @param message Текст сообщения
     * @param date Дата отправки
     * @return Возвращает BaseResponse
     */
    public BaseResponse Create(User sender, String routKey, String message, Date date);

    /**Удаляет сообщение
     * @param message Сообщение
     * @return Возвращает BaseResponse
     */
    public BaseResponse Delete(Message message);

    /**Изменение сообщения.
     * @param message Сообщение
     * @return Возвращает BaseResponse
     */
    public  BaseResponse Change(Message message);

    /**Может ли пользователь изменять сообщение
     * @param message сообщение
     * @param userId id пользователя
     * @return Возвращает BaseResponse
     */
    public  BaseResponse CanChange(Message message,String userId);
}
