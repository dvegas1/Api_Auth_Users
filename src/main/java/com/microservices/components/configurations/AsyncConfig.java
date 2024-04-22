package com.microservices.components.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {
    public static final int CORE_POOL_SIZE = 20;
    public static final int MAX_POOL_SIZE = 50;
    public static final int MAX_POOL_SIZE_QUEUE = 1000;

    @Override
    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(MAX_POOL_SIZE_QUEUE);
        executor.setThreadNamePrefix("LogAudit-");
        executor.initialize();
        return executor;
    }
}
