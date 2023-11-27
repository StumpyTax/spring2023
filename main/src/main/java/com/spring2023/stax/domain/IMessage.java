package com.spring2023.stax.domain;

import java.time.LocalDateTime;

public interface IMessage {
    /**
     * Изменяет текст сообщения
     *
     * @param newText новый текст сообщения
     */
    public void setText(String newText) throws RuntimeException;

    /**
     * Изменяет дату последнего изменения.
     *
     * @param date Дата
     * @throws RuntimeException
     */
    public void setLastChange(LocalDateTime date) throws RuntimeException;
}
