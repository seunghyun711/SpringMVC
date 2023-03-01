package com.example.order;

import com.example.coffee.CoffeeResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
// 주문한 커피 정보인 OrderCoffee를 response body에 포함하기위한 DTO 클래스
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;
    private List<CoffeeResponseDto> orderCoffees;
    private LocalDateTime createdAt;
}
