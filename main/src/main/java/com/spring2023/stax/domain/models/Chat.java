package com.spring2023.stax.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Chat {
    /**
     * Id чата
     */
    @Getter
    private Long id;

    /**
     * Участники чата.
     */
    @Getter
    private List<User> members;
    /**
     * Имя чата
     */
    @Getter
    private String name;
    /**
     * Создатель чата
     */
    @Getter
    private User owner;
    /**
     * Список сообщений чата
     * */
    @Getter
    private List<Message> messages;

}
