package com.example.member;

public class MemberPostDto { // 회원 정보 등록 시 Request Body를 받을 때 사용되는 클래스
    private String email;
    private String name;
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
