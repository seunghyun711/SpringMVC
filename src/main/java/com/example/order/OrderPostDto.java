package com.example.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class OrderPostDto {
    @Positive
    private long memberId;

    // 여러 잔의 커피 주문하게 수정
    @Valid
    private List<OrderCoffeeDto> orderCoffees;

}
