package com.example.spring2023.app;

import com.example.spring2023.domain.User;
import com.example.spring2023.extern.response.BaseResponse;

import java.util.List;

public interface IChatService {
    /** Создает чат
     * @param name Имя чата
     * @param owner Создатель чата
     * @param usersIds ID пользователей
     * @return BaseResponse
     */
    public BaseResponse create(String name, User owner, List<String> usersIds);

    /**Добавляет пользователей в чат
     * @param chatId Id чата
     * @param usersIds ID пользователей
     * @return BaseResponse
     */
    public BaseResponse addUsers(String chatId,List<String> usersIds);

    /**Удаляет пользователя из чата
     * @param chatId Id чата
     * @param userId Id Пользователя
     * @return BaseResponse
     */
    public  BaseResponse deleteUser(String chatId, String userId);

    /**Меняет имя чата
     * @param chatId Id чата
     * @param userId Id Пользователя
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public  BaseResponse changeName(String chatId,String userId,String newName);
}
