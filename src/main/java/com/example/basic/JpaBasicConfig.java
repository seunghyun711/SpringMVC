package com.example.basic;

import com.example.member.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory){ // 엔티티 매니저의 클래스 객체
        this.em = emFactory.createEntityManager(); // EntityManager 클래스 객체를 얻음
        this.tx = em.getTransaction();

        return args -> {
            ex05();
        };
    }

    // 영속성 컨텍스트에 객체 저장
    private void ex01(){
        Member member = new Member("hsh@com");
        em.persist(member); // 영속성 컨텍스트에 member 객체 정보 저장

        Member resultMember = em.find(Member.class,1L); // 영속성 컨텍스트에서 member 객체 조회
        System.out.println("Id : " + resultMember.getMemberId() + ", email : " + resultMember.getEmail());
    }

    // 영속성 컨텍스트와 테이블 엔티티에 저장
    private void ex02(){
        tx.begin(); // Transaction 시작
        Member member = new Member("hsh@com");

        em.persist(member);
        tx.commit(); // 영속성 컨텍스트에 있던 member 객체를 데이터베이스 테이블에 저장

        Member resultMember1 = em.find(Member.class, 1L);
        System.out.println("id : " + resultMember1.getMemberId() + ", email : " + resultMember1.getEmail());

        Member resultMember2 = em.find(Member.class, 2L);
        System.out.println(resultMember2 == null);

    }

    // 쓰기 지연을 통한 영속성 컨텍스트와 테이블에 엔티티 일괄 저장
    private void ex03(){
        tx.begin();
        Member member1 = new Member("hsh1@com");
        Member member2 = new Member("hsh2@com");

        em.persist(member1);
        em.persist(member2);

        tx.commit();
    }

    // 영속성 컨텍스트와 테이블 엔티티 업데이트
    private void ex04(){
        tx.begin();
        em.persist(new Member("hsh@com"));
        tx.commit();

        tx.begin();
        Member member1 = em.find(Member.class, 1L);
        member1.setEmail("hsh@co.kr");
        tx.commit();
    }

    // 영속성 컨텍스트와 테이블의 엔티티 삭제
    private void ex05() {
        tx.begin();
        em.persist(new Member("hsh@com"));
        tx.commit();

        tx.begin();
        Member member = em.find(Member.class, 1L);
        em.remove(member); // 영속성 컨텍스트 1차 캐시에 있는 member 객체 삭제 요청
        tx.commit();
    }
}
