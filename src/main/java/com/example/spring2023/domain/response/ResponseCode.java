package com.example.spring2023.domain.response;

public enum ResponseCode {
    Ok(200),
    Bad(400);
    int code;
    ResponseCode(int code){
        this.code=code;
    }
}
