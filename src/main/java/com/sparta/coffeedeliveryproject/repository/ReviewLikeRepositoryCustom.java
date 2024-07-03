package com.sparta.coffeedeliveryproject.repository;

import com.sparta.coffeedeliveryproject.dto.ReviewResponseDto;
import com.sparta.coffeedeliveryproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepositoryCustom {
    Page<ReviewResponseDto> findLikedReviewsByUserOrderByLikeCreatedAtDesc(User user, Pageable pageable);
}
