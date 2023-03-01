package com.example.member;

import com.example.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    /*
    <mappedBy에는 관계를 소유하고 있는 필드를 지정한다.>
    Member와 Order 간 매핑 관계에서 ORDER 테이블의 외래키로 MEMBER 테이블의 기본키인 MEMBER_ID를 사용한다.
    Order 클래스에서 외래키 역할을 하는 것은 member필드가 될 것이다.따라서 아래 애너테이션에서
    mapperBy에 member가 들어가는 것이다.
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

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

    public void addOrder(Order order) {
        orders.add(order);
    }

    public enum MemberStatus{ // 회원 상태 저장
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면 상태"),
        MEMBER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
