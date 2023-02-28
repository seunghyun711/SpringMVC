package com.example.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity // 해당 클래스는 엔티티 클래스다. -> jpa 관리 대상 엔티티가 된다.
public class Member {
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자 생성 전략
    private long memberId;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Transient // 해당 필드는 테이블 컬럼과 매핑하지 않는 것으로 jpa가 인식
    private String age;


    public Member(String email) {
        this.email = email;
    }

    public Member(long memberId) {
        this.memberId = memberId;
    }

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
