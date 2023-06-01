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


    interface ExpiryDateImmiIngredient {
        String getIngredientName();
        Long countNum();
        FcmToken getFcmToken();
        UserDetail getUserDetail();

    }

//    @Query("SELECT * FROM UserHasIngredient uhi " +
//            "JOIN UserDetail u ON uhi.user = u AND u.isDeleted = false AND u.isBlocked = false " +
//            "JOIN FcmToken fcm ON fcm.user = u AND fcm.fcmToken <> '' " +
//            "WHERE uhi.expirationDate = :")
//    List<ExpiryDateImmiIngredient> findUserHasIngredientsByExpirationDate(LocalDate expiredDate, LocalDate );
}
