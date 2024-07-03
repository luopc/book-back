package com.luopc.platform.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author by Robin
 * @className QuoteProviderApplication
 * @date 2024/1/4 0004 12:13
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class TradingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradingApplication.class, args);
    }
}
