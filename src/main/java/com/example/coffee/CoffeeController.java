package com.example.coffee;

import com.example.coffee.mapper.CoffeeMapper;
import com.example.response.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
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
    public ResponseEntity getCoffees(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Coffee> pageCoffees = coffeeService.findCoffees(page-1, size);
        List<Coffee> coffees = pageCoffees.getContent();

        return new ResponseEntity<>(
            new MultiResponseDto<>(mapper.coffeesToCoffeeResponseDtos(coffees),
                    pageCoffees),
                HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        // No need business logic
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
