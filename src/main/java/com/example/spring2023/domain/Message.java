package com.example.spring2023.domain;

import lombok.Getter;

import java.time.LocalDateTime;

public class Message {
    /**Id сообщения*/
    @Getter
    private String id;
    /**Текст сообщения*/
    @Getter
    private String text;
    /**Дата отправки сообщения*/
    @Getter
    private final LocalDateTime date;
    /**Дата последнего изменения сообщения*/
    private final LocalDateTime lastChange;
    /**Id отправившего сообщение*/
    @Getter
    private final String senderId;
    /**Id получателя сообщения*/
    @Getter
    private final String receiverId;

    public Message(String text,LocalDateTime date,String senderId,String receiverId){
        /*this.id=*/
        this.text=text;
        this.date=date;
        this.lastChange=date;
        this.senderId=senderId;
        this.receiverId=receiverId;

    }
    /**Мегяет текст сообщения
     * @param newText новый текст сообщения
     */
    public void changeText(String newText){
        this.text=newText;
    }
}
