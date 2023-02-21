package com.example.coffee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
public class Coffee {
    @Id
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;

    private String coffeeCode; // 추가된 컬럼 -> Coffee의 중복 체크를 위한 변수

}
