package com.solution.recipetalk.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ExpiryDateImmiNotification {

    @Scheduled(cron = "")
    public void run () {
        
    }
}
