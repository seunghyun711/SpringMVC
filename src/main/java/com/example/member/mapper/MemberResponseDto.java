package com.example.member.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto { // Member클래스를 dto로 변환
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
