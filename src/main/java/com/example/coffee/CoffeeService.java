package com.example.coffee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    public Coffee createCoffee(Coffee coffee) {
        Coffee createdCoffee = coffee;
        return createdCoffee;
    }

    public Coffee updateCoffee(Coffee coffee) {
        Coffee updatedCoffee = coffee;
        return updatedCoffee;
    }

    public Coffee findCoffee(long coffeeId) {
        Coffee coffee = new Coffee(coffeeId, "Americano", "아메리카노", 3000);
        return coffee;
    }

    public List<Coffee> findCoffees() {
        List<Coffee> coffees = List.of(
                new Coffee(1, "Americano", "아메리카노", 3000),
                new Coffee(2, "GreenTea Latte", "녹차라떼", 4000)
        );
        return coffees;
    }

    public void deleteCoffee(long coffeeId) {

    }
}
