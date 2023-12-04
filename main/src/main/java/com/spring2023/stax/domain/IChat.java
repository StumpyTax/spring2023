package com.spring2023.stax.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IChat<T1 extends IUser,T2 extends IMessage > {
    /**
     * Изменяет имя чата.
     *
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    void setName(@NotNull String newName) throws RuntimeException;

    /**
     * Добавляет пользователей
     *
     * @param users пользователи
     */
    void addUsers(List<T1> users);

    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param user UserEntity пользователь
     * @return true или false
     */
    boolean inUsers(@NotNull T1 user);

    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param id id пользователя.
     * @return true или false
     */
    boolean inUsers(Long id);
    /**
     * Удаляет пользователя из чата.
     *
     * @param userId Id пользователя.
     */
    void deleteUser(Long userId);

    /**
     * Меняет владельца чата на пользователя из списка участников
     *
     * @param newOwner id нового владельца
     * @throws RuntimeException
     */
    void changeOwner(Long newOwner) throws RuntimeException;
    boolean deleteOwner();
}
