package com.example.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    // 주문 등록
    @PostMapping
    public ResponseEntity postOrder(@RequestParam("memberId") long memberId,
                                    @RequestParam("coffeeId") long coffeeId){
        Map<String,Long> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("coffeeId",coffeeId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // id로 주문 조회
    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId : " + orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 주문 정보 조회
    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get orders");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
