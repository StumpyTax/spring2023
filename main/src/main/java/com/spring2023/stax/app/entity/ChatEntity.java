package com.spring2023.stax.app.entity;


import com.spring2023.stax.domain.IChat;
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
public class ChatEntity implements IChat<UserEntity,MessageEntity> {
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
    private ArrayList<MessageEntity> messages;

    /**
     * @param name Имя чата
     * @param users Список UserEntity пользователей в чате
     * @param owner UserEntity создателя чата
     */
    public ChatEntity(String name, List<UserEntity> users, UserEntity owner){
        this.name=name;
        this.users=users;
        this.owner=owner;
    }
    /**
     * Изменяет имя чата.
     *
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    @Override
    public void setName(@NotNull String newName) throws RuntimeException {
        if (!newName.replaceAll("\\s+", "").isEmpty())
            name = newName.strip();
        else
            throw new RuntimeException("Incorrect name");
    }

    /**
     * Добавляет пользователей
     *
     * @param users Список UseEntity пользователей
     */
    @Override
    public void addUsers(List<UserEntity> users) {
        this.users.addAll(users);
    }

    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param user UserEntity пользователь
     * @return true или false
     */
    @Override
    public boolean inUsers(@NotNull UserEntity user) {
        return users.stream().anyMatch(x -> x.getId()== user.getId());
    }
    /**
     * Проверяет есть ли пользователь в чате.
     *
     * @param id id пользователя.
     * @return true или false
     */
    @Override
    public boolean inUsers(Long id) {
        return users.stream().anyMatch(x -> x.getId()== id);
    }
    /**
     * Удаляет пользователя из чата.
     *
     * @param userId Id пользователя.
     */
    @Override
    public void deleteUser(Long userId) {
        users.removeIf(x -> x.getId()==(userId));
    }

    /**
     * Изменяет владельца на нового из списка пользователей
     *
     * @param newOwner Новый владелец
     */
    @Override
    public void changeOwner(Long newOwner){
        if(newOwner==owner.getId()){
            throw new RuntimeException("New owner is a Old Owner");
        }
        UserEntity tmpOwner=users.stream().filter(x->x.getId()==newOwner)
                .findFirst().orElseThrow( ()-> new RuntimeException("New owner not in members"));
        this.users.remove(tmpOwner);
        owner=tmpOwner;
    }
    /**
     * Удаляет владельца и заменяет на первого человека в списке
     * */
    @Override
    public  boolean deleteOwner(){
        if(users.isEmpty())
            return false;
        UserEntity tmp=users.get(0);
        owner=tmp;
        users.remove(tmp);
        return true;
    }
}
