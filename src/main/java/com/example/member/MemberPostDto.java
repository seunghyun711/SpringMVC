package com.example.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberPostDto { // 회원 정보 등록 시 Request Body를 받을 때 사용되는 클래스
    // 유효성 검사 적용
    @NotBlank // 공백이 올 수 없다.
    @Email // 유요한 이메일인지 검증, 유효성 검증에 실패하면 내장된 디폴트 에러 메서지가 출력됨.
    private String email;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$" // 정규 표현식 사용
    ,message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
