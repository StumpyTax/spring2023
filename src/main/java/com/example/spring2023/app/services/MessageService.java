package com.example.spring2023.app.services;

import com.example.spring2023.app.repositories.MessageRepository;
import com.example.spring2023.domain.Message;
import com.example.spring2023.domain.User;
import com.example.spring2023.extern.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    /**Создает сообщение
     * @param sender Отправитель сообщения
     * @param routeKey Место назначения сообщения
     * @param message Текст сообщения
     * @param date Дата отправки
     * @return Возвращает BaseResponse
     */
/*
    public BaseResponse create(User sender, String routeKey, String message, Date date);
*/

    /**Удаляет сообщение
     * @param message Сообщение
     * @return Возвращает BaseResponse
     */
/*
    public BaseResponse delete(Message message);
*/

    /**Изменение сообщения.
     * @param message Сообщение
     * @return Возвращает BaseResponse
     */
/*
    public  BaseResponse change(Message message);
*/

    /**Может ли пользователь изменять сообщение
     * @param message сообщение
     * @param userId id пользователя
     * @return Возвращает BaseResponse
     */
/*
    public  BaseResponse canChange(Message message,String userId);
*/
}
