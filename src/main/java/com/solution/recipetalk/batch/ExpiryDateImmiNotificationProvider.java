package com.solution.recipetalk.batch;

import com.solution.recipetalk.domain.ingredient.userhas.repository.UserHasIngredientRepository;
import com.solution.recipetalk.domain.notification.repository.NotificationRepository;
import com.solution.recipetalk.vo.notification.ingredient.userhas.UserHasIngredientNotificationVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpiryDateImmiNotificationProvider {

    private final UserHasIngredientRepository userHasIngredientRepository;
    private final ApplicationEventPublisher eventPublisher;
    //매일 오전 1시에
    @Scheduled(cron = "0 0 1 * * *")
    public void run () {
        long term = 3L;
        LocalDate now = LocalDate.now();
        LocalDate target = now.plusDays(term);
        List<UserHasIngredientRepository.ExpiryDateImmiIngredientDTO> userHasIngredientsByExpirationDate =
                userHasIngredientRepository.findUserHasIngredientsByExpirationDate(now, target);

        log.info(now.toString() + ", scheduling about expired user ingredient count number : " + String.valueOf(userHasIngredientsByExpirationDate.size()));
        userHasIngredientsByExpirationDate.forEach(expiryDateImmiIngredientDTO -> {
            UserHasIngredientNotificationVO vo = new UserHasIngredientNotificationVO(expiryDateImmiIngredientDTO, term);
            eventPublisher.publishEvent(vo);
        });
    }
}
