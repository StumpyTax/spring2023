package com.example.spring2023.app.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "chats")
public class ChatEntity {
    @Id
    @Getter
    @Column(name = "Id",nullable = false)
    @GeneratedValue
    private Long id;
    @Getter
    @Column(name="name",nullable = false)
    private  String name;
    @Getter
    @JoinColumn(name="users_id")
    @OneToMany
    private List<UserEntity> users;
    @Getter
    @JoinColumn(name="owner_id")
    @OneToOne
    private UserEntity owner;

    public ChatEntity(){}



/*    public void setId(Long id) {
        this.id = id;
    }*/


/*
    public void setName(String name) {
        this.name = name;
    }
*/


/*    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }*/


/*    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }*/
}
