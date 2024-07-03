package com.sparta.coffeedeliveryproject.repository;

import com.sparta.coffeedeliveryproject.entity.CafeLike;
import com.sparta.coffeedeliveryproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeLikeRepository extends JpaRepository<CafeLike, Long> {
    Optional<CafeLike> findByCafeCafeIdAndUserUserId(Long cafeId, Long userId);
}
