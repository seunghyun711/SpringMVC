package com.example.order;

import com.example.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ORDERS") // 엔티티 이름을 ORDERS로 지정
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Enumerated(EnumType.STRING) // enum타입과 매핑할 때 사용하는 애너테이션
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne // 다대일 매핑
    @JoinColumn(name = "MEMBER_ID") // Member 테이블과 매핑, MEMBER 테이블의 기본키인 MEMBER_ID를 적어준다.
    private Member member;

    public void addMember(Member member) {
        this.member = member;
    }

    public enum OrderStatus{
        ORDER_REQUEST(1,"주문 요창"),
        ORDER_CONFIRM(2,"주문 확정"),
        ORDER_COMPLETE(3,"주문 완료"),
        ORDER_CANCEL(4,"주문 취소");

        @Getter
        private int stepNumber; // 주문 상태 번호 (1~4)

        @Getter
        private String stepDescription; // 주문 상태 메시지(주문 요청, 주문 확정, 주문 완료, 주문 취소)

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
