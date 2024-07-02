package com.sparta.coffeedeliveryproject.dto;

import com.sparta.coffeedeliveryproject.entity.Cafe;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CafeResponseDto {

    private Long cafeId;

    private String cafeName;

    private String cafeInfo;

    private String cafeAddress;

    private Long cafeLikeCount;

    private LocalDateTime cafeCreatedAt;

    private LocalDateTime cafeModifiedAt;

    public CafeResponseDto(Cafe cafe) {
        this.cafeId = cafe.getCafeId();
        this.cafeName = cafe.getCafeName();
        this.cafeInfo = cafe.getCafeInfo();
        this.cafeAddress = cafe.getCafeAddress();
        this.cafeLikeCount = cafe.getCafeLikeCount();
        this.cafeCreatedAt = cafe.getCreatedAt();
        this.cafeModifiedAt = cafe.getModifiedAt();
    }
}
