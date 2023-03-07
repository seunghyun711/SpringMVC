package com.example.SpringMVC.tdd;

import java.util.regex.Pattern;

public class PasswordValidator {
    public void validate(String password) {
//        boolean containSpecialCharacter =
//                password.chars()
//                        .anyMatch(ch -> !(Character.isDigit(ch) || Character.isAlphabetic(ch)));
        // 패스워드 검증 코드 리펙토링
        if (!Pattern.matches("(?=.*\\W)(?=\\S+$).+",password)) {
            throw new RuntimeException("Invalid Password");
        }
    }
}
