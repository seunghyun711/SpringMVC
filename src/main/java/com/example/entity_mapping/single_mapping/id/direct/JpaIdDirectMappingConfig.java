package com.example.entity_mapping.single_mapping.id.direct;

import com.example.member.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaIdDirectMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            // IDENTITY 전략
//            tx.begin();
//            em.persist(new Member()); // Member 엔티티에 IDENTITY 전략이 적용되어 여기에서 기본키 값은 할당하지 않는다.
//            tx.commit();
//
//            Member member = em.find(Member.class, 1L);
//            System.out.println("# memberId : " + member.getMemberId());

            // SEQUENCE 전략
            tx.begin();
            em.persist(new Member());
            Member member = em.find(Member.class, 1L);
            System.out.println("# memberId : " + member.getMemberId());
            tx.commit();
        };
    }
}
