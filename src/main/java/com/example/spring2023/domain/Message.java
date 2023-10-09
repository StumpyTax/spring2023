package com.example.spring2023.domain;

import com.example.spring2023.domain.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Date;

public class Message {
    /**Id сообщения*/
    @Getter
    private String id;
    /**Текст сообщения*/
    @Getter
    private String text;
    /**Дата отправки сообщения*/
    @Getter
    private final Date date;
    /**Id отправившего сообщение*/
    @Getter
    private final String senderId;
    /**Id получателя сообщения*/
    @Getter
    private final String receiverId;

    public Message(String text,Date date,String senderId,String receiverId){
        /*this.id=*/
        this.text=text;
        this.date=date;
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
