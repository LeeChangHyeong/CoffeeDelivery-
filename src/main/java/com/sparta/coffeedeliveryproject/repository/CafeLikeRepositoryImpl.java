package com.sparta.coffeedeliveryproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.coffeedeliveryproject.dto.CafeResponseDto;
import com.sparta.coffeedeliveryproject.entity.CafeLike;
import com.sparta.coffeedeliveryproject.entity.QCafe;
import com.sparta.coffeedeliveryproject.entity.QCafeLike;
import com.sparta.coffeedeliveryproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CafeLikeRepositoryImpl implements CafeLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CafeResponseDto> findLikedCafesByUserOrderByLikeCreatedAtDesc(User user, Pageable pageable) {
        QCafeLike qCafeLike = QCafeLike.cafeLike;
        QCafe qCafe = QCafe.cafe;

        List<CafeLike> cafeLikes = jpaQueryFactory
                .selectFrom(qCafeLike)
                .join(qCafeLike.cafe, qCafe)
                .where(qCafeLike.user.eq(user))
                .orderBy(qCafeLike.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<CafeResponseDto> responseDtos = cafeLikes.stream()
                .map(cafeLike -> new CafeResponseDto(cafeLike.getCafe()))
                .collect(Collectors.toList());

        long total = jpaQueryFactory
                .selectFrom(qCafeLike)
                .where(qCafeLike.user.eq(user))
                .fetchCount();

        return new PageImpl<>(responseDtos, pageable, total);
    }

    @Override
    public long findLikedCafesCount(User user) {
        QCafeLike qCafeLike = QCafeLike.cafeLike;

        return jpaQueryFactory
                .selectFrom(qCafeLike)
                .where(qCafeLike.user.eq(user))
                .fetchCount();
    }
}
