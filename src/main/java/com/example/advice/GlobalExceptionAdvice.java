package com.example.advice;

import com.example.exception.BusinessLogicException;
import com.example.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // 여러 Controller 클래스에서 @ExceptionHandler, @InitBinder, @ModelAttribute 가 추가된 메서드를 공유해서 사용할 수 있다.
// Controller 클래스에서 발생하는 예외처리를 여기서 다 처리한다 ->  예외 처리를 공통화 한다.
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerConstraintViolationException(ConstraintViolationException e) {

        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());

        return response;
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handlerResourceNotFoundException(RuntimeException e) {
//        System.out.println(e.getMessage());
//        return null;
//    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());

        return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}
