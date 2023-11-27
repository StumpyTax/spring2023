package com.spring2023.stax.app.entity;


import com.spring2023.stax.domain.IChat;
import com.spring2023.stax.domain.IUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "chats")
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity implements IChat {
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

    public ChatEntity(Long id, List<IUser> members, String name, IUser owner) throws RuntimeException{
        this.id=id;
        this.users=members.stream().map(x->(UserEntity)x).toList();
        setName(name);
        this.owner=(UserEntity) owner;
        this.messages=new ArrayList<MessageEntity>();
    }
    public ChatEntity(String name, List<IUser> users, IUser owner){
        this.name=name;
        this.users=users.stream().map(x->(UserEntity)x).toList();
        this.owner=(UserEntity) owner;
    }
    /**
     * Изменяет имя чата.
     *
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public void setName(@NotNull String newName) throws RuntimeException {
        if (!newName.replaceAll("\\s+", "").isEmpty())
            name = newName.strip();
        else
            throw new RuntimeException("Incorrect name");
    }

    /**
     * Добавляет пользователей
     *
     * @param users пользователи
     */
    public void addUsers(List<IUser> users) {
        this.users.addAll(users.stream().
                map(x->(UserEntity)x).toList());
    }

    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param user пользователь
     * @return true или false
     */
    public boolean inUsers(@NotNull IUser user) {
        UserEntity tmp=(UserEntity) user;
        return users.stream().anyMatch(x -> x.getName().equals(tmp.getName())
                && x.getLogin().equals(tmp.getLogin())
                && x.getId()==(tmp.getId())
                && x.getPassword().equals(tmp.getPassword()));
    }

    /**
     * Удаляет пользователя из чата.
     *
     * @param userId Id пользователя.
     */
    public void deleteUser(Long userId) {
        users.removeIf(x -> x.getId()==(userId));
    }

}
