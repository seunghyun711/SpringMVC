package com.example.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class TxConfig {
    private final TransactionManager transactionManager; // 애플리케이션에 트랜잭션을 적용하기 위한 객체

    public TxConfig(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice(){ // 트랜잭션 어드바이스용 TransactionInterceptor 빈 등록
        NameMatchTransactionAttributeSource txAttributeSource =
                new NameMatchTransactionAttributeSource();

        // 조회 메서드를 제외한 공토 트랜잭션 애트리뷰트
        RuleBasedTransactionAttribute txAttribute =
                new RuleBasedTransactionAttribute();
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 조회 메서드에 적용하기 위한 트랜잭션
        RuleBasedTransactionAttribute txFindAttribute =
                new RuleBasedTransactionAttribute();
        txFindAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txFindAttribute.setReadOnly(true);

        // 트랜잭션 적용할 메서드에 트랜잭션 애트리뷰트 매핑
        Map<String, TransactionAttribute> txMethods = new HashMap<>();
        // 각각의 트랜잭션 어트리뷰트 추가
        txMethods.put("find", txFindAttribute);
        txMethods.put("*", txAttribute);

        // 추가한 map 객체를 넘긴다.
        txAttributeSource.setNameMap(txMethods);

        //  TransactionInterceptor 의 생성자 파라미터로 transactionManager와 txAttributeSource를 전달
        return new TransactionInterceptor(transactionManager, txAttributeSource);
    }

    // Advisor 빈 등록
    // 트랜잭션 어드바이스인 TransactionInterceptor 를 타겟 클래스에 적용하기 위해 포인트 컷을 지정
    @Bean
    public Advisor txAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // AspectJExpressionPointcut 객체를 생성한 후, 포인트 컷 표현식으로 CoffeeService 클래스를 타겟 클래스로 지정
        pointcut.setExpression("execution(* com.example.coffee." +
                "CoffeeService.*(..))");

        // DefaultPointcutAdvisor의 생성자 파라미터로 포인트컷과 어드바이스를 전달
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
