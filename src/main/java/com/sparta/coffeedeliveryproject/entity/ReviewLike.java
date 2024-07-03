package com.sparta.coffeedeliveryproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table
@NoArgsConstructor
@EntityListeners(AbstractMethodError.class)
public class ReviewLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;


    public ReviewLike(User user, Review review) {
        this.user = user;
        this.review = review;
    }

}
