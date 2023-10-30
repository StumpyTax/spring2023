package com.example.spring2023.app.entity;


import jakarta.persistence.*;
import lombok.Getter;


import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class MessageEntity {
    @Id
    @Getter
    @GeneratedValue
    @Column(name = "Id",nullable = false)
    private long id;
    @Getter
    @Column(name="text",nullable = false)
    private String text;
    @Getter
    @ManyToOne
    @JoinColumn(name="sender")
    private UserEntity senderId;
    @Getter
    @ManyToOne
    @JoinColumn(name="receiver")
    private UserEntity receiverId;
    @Getter
    @Column(name="sending_date")
    private LocalDateTime date;
    @Getter
    @Column(name="change_date")
    private LocalDateTime lastChange;


    public  MessageEntity(){}
}
