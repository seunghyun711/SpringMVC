package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse { // 에러 정보 중 필요한 정보만 담아서 클라이언트에 전달
    private List<FieldError> fieldErrors; // dto클래스에서 검증해야 하는 멤버 변수들 중 유효성 검증에 실패하는 변수가 하나 이상이 될 수 있기 때문에 배열로 선언

    @Getter
    @AllArgsConstructor
    public static class FieldError{ // ErrorResponse의 static 멤버 클래스
        private String field;
        private Object rejectedValue;
        private String reason;
    }
}
