package com.example.spring2023.app.entity;

import com.example.spring2023.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    @Getter
    private long id;
    @Column(name = "login", nullable = false)
    @Getter
    private String login;
    @Getter
    @Column(name = "password", nullable = false)
    private String password;
    @Getter
    @Column(name = "name", nullable = false)
    private String name;

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
