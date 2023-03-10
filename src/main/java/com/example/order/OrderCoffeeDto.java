package com.example.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;

    @Positive
    private int quantity;
}
