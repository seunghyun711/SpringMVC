package com.example.entity_mapping.single_mapping.column;

import com.example.member.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
// Member 엔티티 클래스의 컬럼 제약 조건 테스트
@Configuration
public class JpaColumnMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;


    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
 //           testEmailNull();
 //           testEmailUpdatable();
            testEmailUnique();
        };
    }

    private void testEmailNull() {
        tx.begin();
        em.persist(new Member());
        tx.commit();
    }

    private void testEmailUpdatable() {
        tx.begin();
        em.persist(new Member("hsh@com"));
        Member member = em.find(Member.class, 1L);
        member.setEmail("hong@com");
        tx.commit();
    }

    private void testEmailUnique(){
        tx.begin();
        em.persist(new Member("hsh@com"));
        em.persist(new Member("hsh@com"));
        tx.commit();
    }
}
