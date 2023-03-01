package com.example.coffee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(nullable = false, length = 50)
    private String engName;
    @Column(nullable = false, length = 50)
    private String korName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 3)
    private String coffeeCode;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false,name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    public Coffee(String engName, String korName, int price) {
        this.engName = engName;
        this.korName = korName;
        this.price = price;
    }

    public Coffee(long coffeeId, String engName, String korName, int price) {
        this.coffeeId = coffeeId;
        this.engName = engName;
        this.korName = korName;
        this.price = price;
    }

    public enum CoffeeStatus{ // 커피 상태 저장
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }
}
