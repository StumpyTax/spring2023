package com.spring2023.stax.app.entity;


import com.spring2023.stax.domain.Chat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Entity
@Table(name = "chats")
@AllArgsConstructor
public class ChatEntity {
    @Id
    @Column(name = "Id",nullable = false)
    @GeneratedValue
    private Long id;
    @Column(name="name",nullable = false)
    private  String name;
    @JoinColumn(name="users")
    @ManyToMany
    private List<UserEntity> users;
    @JoinColumn(name="owner")
    @OneToOne
    private UserEntity owner;
    @OneToMany
    @JoinColumn(name="messages")
    private List<MessageEntity> messages;

    public ChatEntity(){}
    public ChatEntity(String name, List<UserEntity> users, UserEntity owner){
        this.name=name;
        this.users=users;
        this.owner=owner;
    }
    public ChatEntity(@NotNull Chat chat){
        this.id=chat.getId();
        this.name= chat.getName();
        this.users=chat.getMembers().stream().map(UserEntity::new).toList();
        this.owner=new UserEntity(chat.getOwner());
    }

    /** Создает Chat на основе этого ChatEntity
     * @return Чат
     */
    public Chat toChat(){
        return new Chat(id,users.stream().map(UserEntity::toUser).toList(),name,owner.toUser());
    }

}
