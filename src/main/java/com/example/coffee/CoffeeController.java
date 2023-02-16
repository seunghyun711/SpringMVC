package com.example.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/coffees", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoffeeController {

    // 커피 정보 등록
    @PostMapping
    public ResponseEntity postCoffee(@RequestBody CoffeePostDto coffeePostDto){

        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    // id로 커피 정보 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId : " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 커피 정보 조회
    @GetMapping
    public ResponseEntity getCoffees(){
        System.out.println("# get coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 커피 정보 수정(가격만)
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestBody CoffeePatchDto coffeePatchDto){
        coffeePatchDto.setCoffeeId(coffeeId);
        coffeePatchDto.setPrice(6000);

        return new ResponseEntity(coffeePatchDto,HttpStatus.OK);
    }

    // 커피 정보 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
