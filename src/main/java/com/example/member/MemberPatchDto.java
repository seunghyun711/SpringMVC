package com.example.member;

public class MemberPatchDto { // 회원 정보 수정 시 Request Body를 전달 받을 때 사용되는 클래스
    private long memberId;
    private String name;
    private String phone;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
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
