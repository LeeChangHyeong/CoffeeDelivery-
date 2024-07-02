package com.sparta.coffeedeliveryproject.config;// configuration 패키지안에 추가

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JPAConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryMethodFactory() {
        return new JPAQueryFactory(entityManager);
    }
}