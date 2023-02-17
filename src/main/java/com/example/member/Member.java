package com.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 구현
@AllArgsConstructor // 모든 Member클래스의 멤버 변수를 파리미터로 가지는 생성자를 자동으로 구현
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;


}
