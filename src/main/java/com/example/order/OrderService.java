package com.example.order;

import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public Order cretaeOrder(Order order){
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Order findOrder(long orderId){
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public List<Order> findOrders(){
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public void cancelOrder(long orderId){
        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }


}
