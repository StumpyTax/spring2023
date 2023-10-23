package com.example.spring2023.domain;

import com.example.spring2023.extern.response.BaseResponse;
import com.example.spring2023.extern.response.ResponseCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Chat {
    /**Id чата*/
    private String id;
    /**Ключи пользователей в чате*/
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
    public BaseResponse setName(@NotNull String newName){
        if(newName.replaceAll("\\s+", "").length()<0)
            return new BaseResponse(null, ResponseCode.BAD,"Строка из пробелов");
        name=newName;
        return  new BaseResponse(null, ResponseCode.OK,"OK");
    }
}
