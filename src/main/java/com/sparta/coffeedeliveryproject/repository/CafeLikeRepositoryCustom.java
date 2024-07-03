package com.sparta.coffeedeliveryproject.repository;

import com.sparta.coffeedeliveryproject.dto.CafeResponseDto;
import com.sparta.coffeedeliveryproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
@Repository
public interface CafeLikeRepositoryCustom {
    Page<CafeResponseDto> findLikedCafesByUserOrderByLikeCreatedAtDesc(User user, Pageable pageable);
}
