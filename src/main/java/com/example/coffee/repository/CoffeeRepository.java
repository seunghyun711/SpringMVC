package com.example.coffee.repository;

import com.example.coffee.Coffee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode); // 쿼리 메서드

    @Query("SELECT * FROM COFFEE WHERE COFFEE_ID = :coffeeId") // coffeeId는 findByCoffee에 채워지는 동적 파라미터
    Optional<Coffee> findByCoffee(long coffeeId);
}
