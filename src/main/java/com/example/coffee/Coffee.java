package com.example.coffee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Coffee {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;

}
