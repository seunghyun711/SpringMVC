package com.example.order.mapper;

import com.example.coffee.Coffee;
import com.example.order.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);

    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
}
