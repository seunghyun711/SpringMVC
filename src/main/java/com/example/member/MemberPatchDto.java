package com.example.member;

import javax.validation.constraints.Pattern;

public class MemberPatchDto { // 회원 정보 수정 시 Request Body를 전달 받을 때 사용되는 클래스
    private long memberId;

    // 공백 아닌 문자 1개 이상((공백인 문자 0개 또는 1개)(공백이 아닌 문자 1개 이상)) -> 마지막 맨 바깥 쪽 괄호 조건이 0개 이상(즉, 있어도 되고 없어도 된다)
    @Pattern(regexp = "^\\S+(\\s?\\S+)*$",
    message = "회원 이름은 공백이 올 수 없습니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
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
