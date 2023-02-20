package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse { // 에러 정보 중 필요한 정보만 담아서 클라이언트에 전달
    private List<FieldError> fieldErrors; // dto클래스에서 검증해야 하는 멤버 변수들 중 유효성 검증에 실패하는 변수가 하나 이상이 될 수 있기 때문에 배열로 선언
    private List<ConstraintViolationError> violationErrors; // URI 변수로 넘어오는 값의 유효성 검증에 대한 에러

    public ErrorResponse(List<FieldError> fieldErrors, List<ConstraintViolationError> violationErrors) {
        this.fieldErrors = fieldErrors;
        this.violationErrors = violationErrors;
    }

    // BindingResult에 대한 ErrorResponse객체 생성
    public static ErrorResponse of(BindingResult bindingResult){
        return new ErrorResponse(FieldError.of(bindingResult),null);
    }

    // Set<ConstraintViolation<?>> 객체에 대한 ErrorResponse 객체 생성
    public static ErrorResponse of(Set<ConstraintViolation<?>> violations){
        return new ErrorResponse(null,ConstraintViolationError.of(violations));
    }

    // FieldError 가공
    @Getter
    @AllArgsConstructor
    public static class FieldError{ // ErrorResponse의 static 멤버 클래스
        private String field;
        private Object rejectedValue;
        private String reason;

        // BindingResult에 대한 ErrorResponse 객체 생성
        public static List<FieldError> of(BindingResult bindingResult){
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ?
                                    "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }

    // ConstraintViolation Error 가공
    @Getter
    @AllArgsConstructor
    public static class ConstraintViolationError{
        private String propertyPath;
        private Object rejectedValue;
        private String reason;

        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations){
            return constraintViolations.stream()
                    .map(constraintViolation -> new ConstraintViolationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getInvalidValue().toString(),
                            constraintViolation.getMessage()))
                    .collect(Collectors.toList());
        }
    }
}
