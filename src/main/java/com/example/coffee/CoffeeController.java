package com.example.coffee;

import com.example.coffee.mapper.CoffeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/coffees")
@Validated
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = mapper.coffeePostDtoToCoffee(coffeePostDto);
        Coffee response = coffeeService.createCoffee(coffee);
        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response), HttpStatus.CREATED);
    }

    // 커피 정보 수정(가격만)
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Min(1) long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto){
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee response = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(response),HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        Coffee coffee = coffeeService.findCoffee(coffeeId);


        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> response =
                coffees.stream()
                        .map(coffee -> mapper.coffeeToCoffeeResponseDto(coffee))
                        .collect(Collectors.toList());

        // not implementation

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // No need business logic
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
