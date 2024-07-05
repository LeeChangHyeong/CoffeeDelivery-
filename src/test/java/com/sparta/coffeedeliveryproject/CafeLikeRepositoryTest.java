package com.sparta.coffeedeliveryproject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.coffeedeliveryproject.dto.CafeResponseDto;
import com.sparta.coffeedeliveryproject.entity.Cafe;
import com.sparta.coffeedeliveryproject.entity.CafeLike;
import com.sparta.coffeedeliveryproject.entity.User;
import com.sparta.coffeedeliveryproject.repository.CafeLikeRepository;
import com.sparta.coffeedeliveryproject.repository.CafeLikeRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@AutoConfigureTestDatabase(replace = NONE)
@Import(TestConfig.class)
@DataJpaTest
public class CafeLikeRepositoryTest {

    @Autowired
    private CafeLikeRepositoryCustom cafeLikeRepositoryCustom;

    @PersistenceContext
    private EntityManager em;

    private User user;
    private Cafe cafe1, cafe2;

    @BeforeEach
    void setUp() {
        user = new User("lchNumber9", "Dlckdgud11!", "이창형");
        cafe1 = new Cafe("카페 이름1", "카페 정보1", "카페 주소1");
        cafe2 = new Cafe("카페 이름2", "카페 정보2", "카페 주소2");

        // 엔티티 매니저를 사용하여 데이터 저장
        em.persist(user);
        em.persist(cafe1);
        em.persist(cafe2);

        // 좋아요 데이터 생성
        em.persist(new CafeLike(user, cafe1));
        em.persist(new CafeLike(user, cafe2));

        em.flush();
        em.clear();
    }

    @Test
    public void findLikedCafesByUserOrderByLikeCreatedAtDescTest() {
        PageRequest pageable = PageRequest.of(0, 5);
        Page<CafeResponseDto> result = cafeLikeRepositoryCustom.findLikedCafesByUserOrderByLikeCreatedAtDesc(user, pageable);

        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getCafeName()).isEqualTo("카페 이름2");
        assertThat(result.getContent().get(1).getCafeName()).isEqualTo("카페 이름1");
    }
}
