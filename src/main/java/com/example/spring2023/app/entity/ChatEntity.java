package com.example.spring2023.app.entity;


import com.example.spring2023.domain.Chat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
@Table(name = "chats")
@AllArgsConstructor
public class ChatEntity {
    @Id
    @Getter
    @Column(name = "Id", nullable = false)
    @GeneratedValue
    private Long id;
    @Getter
    @Column(name = "name", nullable = false)
    private String name;
    @Getter
    @JoinColumn(name = "users")
    @ManyToMany
    private List<UserEntity> users;
    @Getter
    @JoinColumn(name = "owner")
    @OneToOne
    private UserEntity owner;

    public ChatEntity() {
    }

    public ChatEntity(String name, List<UserEntity> users, UserEntity owner) {
        this.name = name;
        this.users = users;
        this.owner = owner;
    }

    public ChatEntity(@NotNull Chat chat) {
        this.id = chat.getId();
        this.name = chat.getName();
        this.users = chat.getMembers().stream().map(UserEntity::new).toList();
        this.owner = new UserEntity(chat.getOwner());
    }

    /**
     * Создает Chat на основе этого ChatEntity
     *
     * @return Чат
     */
    public Chat toChat() {
        return new Chat(id, users.stream().map(UserEntity::toUser).toList(), name, owner.toUser());
    }

}
