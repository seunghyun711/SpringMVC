package com.example.multi_mapping;

import com.example.member.Member;
import com.example.order.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaManyToOneUnDirectionConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaManyToOneRunner(EntityManagerFactory emFactory){
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            mappingManyToOneUniDirection();
        };
    }

    private void mappingManyToOneUniDirection() {
        tx.begin();
        Member member = new Member("hong@com", "hong", "010-2222-2222");

        em.persist(member);

        Order order = new Order();
        order.addMember(member); // MEMBER_ID 같은 외래키 역할을 한다.
        em.persist(order); // 주문 정보 저장

        tx.commit();

        Order findOrder = em.find(Order.class, 1L); // 등록한 회원에 해당하는 주문을 조회

        // 주문에 해당하는 회원 정보를 가져온다.
        System.out.println("findOrder : " + findOrder.getMember().getMemberId() +
                ", " + findOrder.getMember().getEmail());
        // findOrder.getMember().getMemberId()와 같이 객체를 통해 다른 객체의 정보를 가져오는 것읋 객체 그래프 탐색이라 한다.
    }

}
