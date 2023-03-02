package com.example.order;

import com.example.coffee.CoffeeService;
import com.example.exception.BusinessLogicException;
import com.example.exception.ExceptionCode;
import com.example.member.Member;
import com.example.member.MemberService;
import com.example.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {
    private final MemberService memberService;
    private final OrderRepository orderRepository;
    private final CoffeeService coffeeService;

    public OrderService(MemberService memberService, OrderRepository orderRepository, CoffeeService coffeeService) {
        this.memberService = memberService;
        this.orderRepository = orderRepository;
        this.coffeeService = coffeeService;
    }
    /*
    1. 주문 검증
        1-1. 주문한 회원 존재하는지 검증
        1-2. 주문한 커피 존재하는지 검증
    2. 주문 저장
    3. 주문한 커피 수 만큼 스탬프 증가
     */

    public Order createOrder(Order order) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(order.getMember().getMemberId());

        // 커피가 존재하는지 조회
        // 1. 주문 검증
        verifyExistOrder(order);
        // 2. 주문 저장
        Order reportOrder = orderRepository.save(order);
        // 3. 주문한 커피 수 만큼 스탬프 증가
        updateStamp(reportOrder);

        return reportOrder;
    }

    public Order updateOrder(Order order) {
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus(orderStatus));
        findOrder.setModifiedAt(LocalDateTime.now());
        return orderRepository.save(findOrder);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderId").descending()));
    }

    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();
        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        findOrder.setModifiedAt(LocalDateTime.now());
        orderRepository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }

    private void verifyExistOrder(Order order) {
        // 1-1. 회원이 존재하는지 검증
        memberService.findVerifiedMember(order.getMember().getMemberId());

        // 1-2. 주문한 커피 검증
        order.getOrderCoffees().stream()
                .forEach(orderCoffee -> coffeeService.findVerifiedCoffee(orderCoffee.getCoffee().getCoffeeId()));
    }

    // 스탬프 업데이트 메서드
    private void updateStamp(Order order) {
        Member member = memberService.findVerifiedMember(order.getMember().getMemberId());

        // 스탬프 개수
        int stampCount = calcStamp(order);

        Stamp stamp = member.getStamp();
        stamp.setStampCount(stamp.getStampCount() + stampCount);
        member.setStamp(stamp);

        memberService.updateMember(member);
    }

    // 스탬프 개수 계산 메서드
    private int calcStamp(Order order) {
        return order.getOrderCoffees().stream()
                .map(orderCoffee -> orderCoffee.getQuantity())
                .mapToInt(orderCoffeeQuantity -> orderCoffeeQuantity)
                .sum();
    }
}
