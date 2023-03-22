package com.solution.recipetalk.async.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@EnableAsync //@Async를 사용할 수 있게 함.
@Configuration
public class AsyncConfig {

    @Bean(name = "Notification") //사용할 땐 @Async("Notification")
    public Executor threadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        log.info("processors count {}", processors);

        executor.setThreadNamePrefix("Notification-");
        executor.setCorePoolSize(processors);
        executor.setMaxPoolSize(processors*2);
        executor.setQueueCapacity(50); // 최대 큐 수
        executor.setKeepAliveSeconds(60); // 큐 속에 들어가지 못한 데이터는 60초 후에 삭제.
        executor.initialize();
        return executor;

    }
}
