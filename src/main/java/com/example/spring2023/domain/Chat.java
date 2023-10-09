package com.example.spring2023.domain;

import com.example.spring2023.domain.response.BaseResponse;
import com.example.spring2023.domain.response.ResponseCode;
import lombok.Getter;

import java.util.List;

public class Chat {
    /**Id чата*/
    private String id;
    /**Ключи пользователей в чате*/
    @Getter
    private List<String> routKeys;
    /**Участники чата.
     * */
    private List<String> membersIds;
    /**Имя чата*/
    @Getter
    private String name;
    @Getter
    private String  ownerId;
    public  Chat(List<String> routKeys,List<String> membersIds,String name,String ownerId){
        this.name=name;
        this.ownerId=ownerId;
        this.membersIds=membersIds;
        this.routKeys=routKeys;
    }
    /** Изменяет имя чата.
     * @param newName Новое имя чата
     * @return BaseResponse
     */
    public BaseResponse SetName(String newName){
        if(newName.replaceAll("\\s+", "").length()<0)
            return new BaseResponse(null, ResponseCode.Bad,"Строка из пробелов");
        name=newName;
        return  new BaseResponse(null, ResponseCode.Ok,"OK");
    }
}
