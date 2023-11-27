package com.spring2023.stax.app.entity;

import com.spring2023.stax.domain.IUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements IUser {
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
    public UserEntity(String name, String login,String password) throws RuntimeException{
        this.name=name;
        this.login=login;
        this.password=password;
    }
    public UserEntity(Long id,String name,String login,String password) throws RuntimeException{
        this.id=id;
        setName(name);
        setLogin(login);
        setPassword(password);
    }
    public void setPassword(String newPassword) throws RuntimeException{
        if(!(newPassword.contains(" ") || newPassword.isEmpty()))
            password=newPassword;
        else
            throw new RuntimeException("Incorrect password");
    }
    public void setName(String newName) throws RuntimeException {
        if (!newName.replaceAll("\\s+", "").isEmpty())
            name = newName.strip();
        else
            throw new RuntimeException("Incorrect name");
    }

    public void setLogin(String newLogin) throws RuntimeException {
        if (newLogin.replaceAll("\\w", "").isEmpty() && !newLogin.isEmpty())
            login = newLogin.strip();
        else
            throw new RuntimeException("Incorrect login");
    }
}
