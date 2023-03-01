package com.example.order;

import com.example.coffee.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderCoffeeId;

    // Order와 다대일 매핑
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order) {
        this.order = order;
    }

    // COffee와 다대일 매핑
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    public void addCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Column(nullable = false)
    private int quantity; // 수량
}
