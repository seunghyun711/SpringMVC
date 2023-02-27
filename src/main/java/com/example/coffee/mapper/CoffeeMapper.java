package com.example.coffee.mapper;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeePatchDto;
import com.example.coffee.CoffeePostDto;
import com.example.coffee.CoffeeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    // CoffeePostDto -> Coffee
    public Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    // CoffeePatchDto -> Coffee
    public Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    // Coffee -> CoffeeResponseDto
    public CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
}
