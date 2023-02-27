package com.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // 해당 클래스는 엔티티 클래스다. -> jpa 관리 대상 엔티티가 된다.
public class Member {
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 생성 전략
    private long memberId;
    private String email;
    private String name;
    private String phone;

    public Member(String email) {
        this.email = email;
    }

    public Member(long memberId) {
        this.memberId = memberId;
    }
}
