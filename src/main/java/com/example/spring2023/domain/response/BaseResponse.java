package com.example.spring2023.domain.response;

import lombok.Getter;
import org.springframework.lang.Nullable;

public class BaseResponse {
    /**Результат, который необходимо вернуть*/
    @Getter
    @Nullable
    private final String res;
    /**Код*/
    @Getter
    private final ResponseCode code;
    /**Сообщение*/
    @Getter
    private final String message;

    public BaseResponse(String res, ResponseCode code, String message) {
        this.res = res;
        this.code = code;
        this.message=message;
    }
}
