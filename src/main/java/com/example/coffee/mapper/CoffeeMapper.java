package com.example.coffee.mapper;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeePatchDto;
import com.example.coffee.CoffeePostDto;
import com.example.coffee.CoffeeResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto>coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
