package com.example.order.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Table("ORDER_COFFEE")
public class CoffeeRef {
    @Id
    private long orderCoffeeId;
    private long coffeeId;
    private int quantity;

}
