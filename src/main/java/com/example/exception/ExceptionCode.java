package com.example.exception;

import lombok.Getter;

public enum ExceptionCode { // 예외 코드 정의
    MEMBER_NOT_FOUND(404,"Member Not Found");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
