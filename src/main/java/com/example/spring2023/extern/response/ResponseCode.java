package com.example.spring2023.extern.response;

public enum ResponseCode {
    OK(200),
    BAD(400),
    PARTIAL_CONTENT(206),
    NO_CONTENT(204);
    int code;
    ResponseCode(int code){
        this.code=code;
    }
}
