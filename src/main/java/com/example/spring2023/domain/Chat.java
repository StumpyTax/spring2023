package com.example.spring2023.domain;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Chat {
    /**Id чата*/
    private String id;
   /* /**Ключи пользователей в чате*/
    /*@Getter
    private List<String> routeKeys;*/
    /**Участники чата.
     * */
    private List<String> membersIds;
    /**Имя чата*/
    @Getter
    private String name;
    @Getter
    private String  ownerId;
    public  Chat(/*List<String> routeKeys,*/List<String> membersIds,String name,String ownerId){
        this.name=name;
        this.ownerId=ownerId;
        this.membersIds=membersIds;
        /*this.rouetKeys=routeKeys;*/
    }
    /** Изменяет имя чата.
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public void setName(@NotNull String newName){
        newName=newName.replaceAll("\\s+", "");
        if(!newName.isEmpty())
            name=newName;
    }
}
