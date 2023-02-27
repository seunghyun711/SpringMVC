package com.example.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class MemberDto {
    @Email
    private String email;
    private String name;
    private String phone;
}
