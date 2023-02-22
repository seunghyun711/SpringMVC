package com.example.order.mapper;

import com.example.coffee.Coffee;
import com.example.coffee.CoffeeResponseDto;
import com.example.coffee.service.CoffeeService;
import com.example.order.OrderCoffeeResponseDto;
import com.example.order.OrderPostDto;
import com.example.order.OrderResponseDto;
import com.example.order.entity.CoffeeRef;
import com.example.order.entity.Order;
import org.mapstruct.Mapper;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(new AggregateReference.IdOnlyAggregateReference( // long타입인 memberId를 IdOnlyAggregateReference의 생성자 파라미터로 전달
                orderPostDto.getMemberId()));
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto ->
                        // (2-1)
                        CoffeeRef.builder()
                                .coffeeId(orderCoffeeDto.getCoffeeId())
                                .quantity(orderCoffeeDto.getQuantity())
                                .build())
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);

        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService,
                                                     Order order) {
        long memberId = order.getMemberId().getId();

        List<OrderCoffeeResponseDto> orderCoffees =
                orderToOrderCoffeeResponseDtos(coffeeService, order.getOrderCoffees());
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        // TODO 주문에 대한 더 자세한 정보로의 변환은 요구 사항에 따라 다를 수 있습니다.

        return orderResponseDto;
    }

    default List<OrderCoffeeResponseDto> orderToOrderCoffeeResponseDtos(CoffeeService coffeeService,
                                                                        Set<CoffeeRef> orderCoffees) {
        return orderCoffees.stream()
                .map(coffeeRef -> {
                    Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());
                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            coffeeRef.getQuantity());
                }).collect(Collectors.toList());
    }
}
