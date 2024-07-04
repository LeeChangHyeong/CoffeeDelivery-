package com.sparta.coffeedeliveryproject.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.coffeedeliveryproject.dto.ReviewResponseDto;
import com.sparta.coffeedeliveryproject.entity.QReview;
import com.sparta.coffeedeliveryproject.entity.QReviewLike;
import com.sparta.coffeedeliveryproject.entity.ReviewLike;
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
public class ReviewLikeRepositoryImpl implements ReviewLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReviewResponseDto> findLikedReviewsByUserOrderByLikeCreatedAtDesc(User user, Pageable pageable) {
        QReviewLike qReviewLike = QReviewLike.reviewLike;
        QReview qReview = QReview.review;

        List<ReviewLike> reviewLikes = jpaQueryFactory
                .selectFrom(qReviewLike)
                .join(qReviewLike.review, qReview)
                .where(qReviewLike.user.eq(user))
                .orderBy(qReviewLike.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<ReviewResponseDto> responseDtos = reviewLikes.stream()
                .map(reviewLike -> new ReviewResponseDto(reviewLike.getReview()))
                .collect(Collectors.toList());

        long total = jpaQueryFactory
                .selectFrom(qReviewLike)
                .where(qReviewLike.user.eq(user))
                .fetchCount();

        return new PageImpl<>(responseDtos, pageable, total);
    }

    @Override
    public long findLikedReviewsCount(User user) {
        QReviewLike qReviewLike = QReviewLike.reviewLike;

        return jpaQueryFactory
                .selectFrom(qReviewLike)
                .where(qReviewLike.user.eq(user))
                .fetchCount();
    }
}
