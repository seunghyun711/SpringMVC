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
    public ResponseEntity postCoffee(@RequestParam("engName") String engName,
                                     @RequestParam("korName") String korName,
                                     @RequestParam("price") int price){
        Map<String, Object> map = new HashMap<>();
        map.put("engName",engName);
        map.put("korName",korName);
        map.put("price",price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
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

}
