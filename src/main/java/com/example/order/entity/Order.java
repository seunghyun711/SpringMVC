package com.example.order.entity;

import com.example.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Table("ORDERS") // 테이블 명을 ORDERS로 지정
public class Order {
    @Id
    private long orderId;

    // 테이블 외래키처럼 memberId를 추가하여 참조
    private AggregateReference<Member, Long> memberId; // 애그리거트 루트인 Member와 Order간 id로 참조

    // Order와 Coffee는 n:n관계로 CoffeeRef라는 중간 엔티티를 만들어 Order와 Coffee의 연관관계를 매핑한다.
    // CoffeeRef는 Order 클래스와 동일한 애그리거트에 있기 때문에 객체 참조로 매핑한다.
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDER_COFFEE_ID")
    // idColumn : 외래키, keyColumn ; 기본키
    private Set<CoffeeRef> orderCoffees = new LinkedHashSet<>();

    // 주문 상태 정보
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    // 주문 등록 시간
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum OrderStatus{ // 주문의 상태를 나타내는 enum
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;
        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
