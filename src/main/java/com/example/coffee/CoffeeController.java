package com.example.coffee;

import com.example.coffee.mapper.CoffeeMapper;
import com.example.coffee.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/coffees", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoffeeController {

    private CoffeeService coffeeService;
    private CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService, CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    // 커피 정보 등록
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto){
        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.CREATED);
    }

    // id로 커피 정보 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> response = mapper.coffeesToCoffeeResponseDtos(coffees);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 전체 커피 정보 조회
    @GetMapping
    public ResponseEntity getCoffees(){
        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> response = mapper.coffeesToCoffeeResponseDtos(coffees);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // 커피 정보 수정(가격만)
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto){
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));

        return new ResponseEntity<>(mapper.coffeeToCoffeeResponseDto(coffee), HttpStatus.OK);
    }

    // 커피 정보 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId){
        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
