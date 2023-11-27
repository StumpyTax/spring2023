package com.spring2023.stax.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IChat {
    /**
     * Изменяет имя чата.
     *
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public void setName(@NotNull String newName) throws RuntimeException;

    /**
     * Добавляет пользователей
     *
     * @param users пользователи
     */
    public void addUsers(List<IUser> users);

    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param user пользователь
     * @return true или false
     */
    public boolean inUsers(@NotNull IUser user);

    /**
     * Удаляет пользователя из чата.
     *
     * @param userId Id пользователя.
     */
    public void deleteUser(Long userId);
}
