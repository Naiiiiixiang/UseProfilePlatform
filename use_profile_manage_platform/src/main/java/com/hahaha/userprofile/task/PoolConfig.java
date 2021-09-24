package com.hahaha.userprofile.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author: naixiang
 * @Date: 2021/9/24 18:53
 * @Description:
 **/
@Configuration
@EnableAsync
public class PoolConfig {
    @Bean
    public ThreadPoolTaskExecutor getPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /*
         * 线程数
         */
        threadPoolTaskExecutor.setCorePoolSize(10);
        /*
         * 等待队列容量 ，线程数不够任务会等待
         */
        threadPoolTaskExecutor.setQueueCapacity(1000);
        /*
         * 最大线程数，等待数不够会增加线程数，直到达此上线  超过这个范围会抛异常
         */
        threadPoolTaskExecutor.setMaxPoolSize(30);
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }

    @Bean
    public Executor getAsyncExecutor() {
        return getPoolExecutor();
    }
}
