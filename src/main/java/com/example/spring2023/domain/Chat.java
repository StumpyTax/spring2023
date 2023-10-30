package com.example.spring2023.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    /**Id чата*/
    @Getter
    private Long id;
   /* /**Ключи пользователей в чате*/
    /*@Getter
    private List<String> routeKeys;*/
    /**Участники чата.
     * */
    @Getter
    private List<User> members;
    /**Имя чата*/
    @Getter
    private String name;
    /**Создатель чата*/
    @Getter
    private User  owner;

    /** Изменяет имя чата.
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public void setName(@NotNull String newName){
        newName=newName.replaceAll("\\s+", "");
        if(!newName.isEmpty())
            name=newName;
    }

    /**Добавляет пользователей
     * @param users пользователи
     */
    public void addUsers(List<User> users){
        this.members.addAll(users);
    }

    /**Проверяет есть ли пользователь в чате.
     * @param user пользователь
     * @return true или false
     */
    public boolean inUsers(@NotNull User user){
        return members.stream().anyMatch(x -> x.getName().equals(user.getName())
                                && x.getLogin().equals(user.getLogin())
                                && x.getId().equals(user.getId())
                                && x.getPassword().equals(user.getPassword()));
    }

    /**Удаляет пользователя из чата.
     * @param userId Id пользователя.
     */
    public void deleteUser(Long userId){
       members.removeIf(x->x.getId().equals(userId));
    }
}
