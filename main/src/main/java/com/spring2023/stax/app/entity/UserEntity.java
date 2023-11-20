package com.spring2023.stax.app.entity;

import com.spring2023.stax.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany
    @JoinColumn(name="chats")
    private List<ChatEntity> chats;
    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public User toUser() {
        return new User(this.id, this.name, this.login, this.password);
    }
}
