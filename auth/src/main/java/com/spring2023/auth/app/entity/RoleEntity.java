package com.spring2023.auth.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "roles")
@Entity
@Data
public class RoleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;
}

