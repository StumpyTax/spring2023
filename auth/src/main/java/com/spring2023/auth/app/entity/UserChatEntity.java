package com.spring2023.auth.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "userChat")
public class UserChatEntity {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="chatId")
    private Long chatId;
}
