package com.example.coffee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    // 커피 정보 등록
    public Coffee createCoffee(Coffee coffee){
        Coffee createdCoffee = coffee;
        return createdCoffee;
    }

    // 커피 정보 수정
    public Coffee updateCoffee(Coffee coffee) {
        Coffee updatedCoffee = coffee;
        return updatedCoffee;
    }

    // id로 커피 정보 조회
    public Coffee findCoffee(long coffeeId) {
        Coffee coffee = new Coffee(coffeeId, "아메리카노", "americano", 3000);
        return coffee;
    }

    public List<Coffee> findCoffees() {
        List<Coffee> coffees = List.of(
                new Coffee(1,"아메리카노", "americano", 3000),
                new Coffee(2,"녹차 라떼", "greenTea Latte", 4000)
        );
        return coffees;
    }

    // 커피 정보 삭제
    public void deleteCoffee(long coffeeId) {

    }


}
