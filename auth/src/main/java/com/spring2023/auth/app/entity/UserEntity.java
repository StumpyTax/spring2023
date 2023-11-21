package com.spring2023.auth.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Table(name = "users")
@Entity
@Data
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Getter
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<com.spring2023.auth.app.entity.RoleEntity> roles;
}
