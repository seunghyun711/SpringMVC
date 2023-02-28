package com.example.entity_mapping.multi_mapping;

import com.example.member.Member;
import com.example.order.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneBiDirectionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneBiDirection();
        };
    }

    private void mappingManyToOneBiDirection() {
        tx.begin();
        Member member = new Member("hong@com", "hong", "010-2222-2222");
        Order order = new Order();

        /*
        member 객체에 order 객체를 추가하지 않아도 테이블에는 member 정보와 order 정보가 정상적으로 저장된다.
        하지만 member 객체에 order 객체를 추가하지 않으면 find() 메서드로 조회한 member 객체로 order를 그래프 탐색하면
        order 객체를 조회할 수 없다. 왜냐하면 find() 메서드가 1차 캐시에서 member 객체를 조회하는데 order를 추가하지 않으면
        1차 캐시에 저장된 member 객체는 order 객체를 가지고 있지 않기 때문이다.
         */
        member.addOrder(order);

        /*
        member가 order의 외래키 역할을 하기 때문에 order 객체 저장시 member 객체는 필요하다.
         */
        order.addMember(member);

        em.persist(member); // 회원 정보 저징
        em.persist(order); // 주문 정보 저장

        tx.commit();
        Member findMember = em.find(Member.class, 1L); // 저장한 회원 정보 조회

        // 주문한 회원의 회원 정보를 통해 주문 정보를 가져올 수 있다.
        findMember
                .getOrders()
                .stream()
                .forEach(findOrder ->{
                    System.out.println("findMember : " +
                            findOrder.getOrderId() + ". " +
                            findOrder.getOrderStatus());
                });
    }
}
