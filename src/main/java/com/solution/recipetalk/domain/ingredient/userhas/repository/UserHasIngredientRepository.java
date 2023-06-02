package com.solution.recipetalk.domain.ingredient.userhas.repository;

import com.solution.recipetalk.domain.fcm.entity.FcmToken;
import com.solution.recipetalk.domain.ingredient.userhas.entity.UserHasIngredient;
import com.solution.recipetalk.domain.ingredient.userhas.repository.custom.UserHasIngredientCustomRepository;
import com.solution.recipetalk.domain.user.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserHasIngredientRepository extends JpaRepository<UserHasIngredient, Long>, UserHasIngredientCustomRepository {

    void deleteAllByUser_Id(Long userId);


    //클라이언트로 보낼 목적이 아니기 때문에... 한번 더 가공해야 함.
    interface ExpiryDateImmiIngredientDTO {
        String getIngredientName();
        Long getCountNum();
        FcmToken getFcmToken();
        UserDetail getUserDetail();

    }

    // 인원 많아 지면 userDetail id 쪼개서 받아오는 로직 필요함. (last id Paging 방식)
    @Query("SELECT MAX(uhi.name) as ingredientName, count(uhi) as countNum,  u as userDetail FROM UserHasIngredient uhi " +
            "JOIN UserDetail u ON uhi.user = u AND u.isDeleted = false AND u.isBlocked = false " +
            "WHERE uhi.expirationDate between :now and :target " +
            "group by u")
    List<ExpiryDateImmiIngredientDTO> findUserHasIngredientsByExpirationDate(LocalDate now, LocalDate target);

}
