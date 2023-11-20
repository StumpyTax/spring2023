package com.spring2023.stax.domain;

import lombok.Getter;

import java.time.LocalDateTime;
public class Message {
    /**
     * Id сообщения
     */
    @Getter
    private Long id;
    /**
     * Текст сообщения
     */
    @Getter
    private String text;
    /**
     * Дата отправки сообщения
     */
    @Getter
    private final LocalDateTime date;
    /**
     * Дата последнего изменения сообщения
     */
    @Getter
    private LocalDateTime lastChange;
    /**
     * Отправитель сообщение
     */
    @Getter
    private final User sender;
    /**
     * Получатель сообщения
     */
    @Getter
    private final Chat receiver;

    public Message(String text, LocalDateTime date, User sender, Chat receiver)throws RuntimeException {
        /*this.id=*/
        setText(text);
        this.date = date;
        this.lastChange = date;
        this.sender = sender;
        this.receiver = receiver;
    }
    public Message(Long id,String text, LocalDateTime date,LocalDateTime lastChange, User sender, Chat receiver)
            throws RuntimeException {
        this.id=id;
        setText(text);
        this.date = date;
        setLastChange(lastChange);
        this.sender = sender;
        this.receiver = receiver;
    }
    /**
     * Изменяет текст сообщения
     *
     * @param newText новый текст сообщения
     */
    public void setText(String newText) throws RuntimeException{
        if (!newText.replaceAll(" ","").isEmpty())
            this.text = newText.strip();
        else
            throw new RuntimeException("Incorrect text");
    }

    /**
     * Изменяет дату последнего изменения.
     *
     * @param date Дата
     * @throws RuntimeException
     */
    public void setLastChange(LocalDateTime date) throws RuntimeException{
        if(date.isAfter(lastChange))
            this.lastChange=date;
        else
            throw new RuntimeException("Incorrect date");
    }
}
