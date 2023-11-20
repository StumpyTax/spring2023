package com.spring2023.stax.extern.response;

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
