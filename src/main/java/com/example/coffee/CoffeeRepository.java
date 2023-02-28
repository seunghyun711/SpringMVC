package com.example.coffee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    // JPQL을 통한 객체 지향 쿼리 사용
    @Query(value = "SELECT c FROM Coffee c WHERE c.coffeeId = :coffeeId")
    Optional<Coffee> findByCoffee(long coffeeId);
}
