package com.cofjus.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author Rui
 * @Date 2021/10/4 19:09
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
public class EasyChatApplication {
    public static void main(String... args) {
        SpringApplication.run(EasyChatApplication.class);
        log.info("success");
    }
}
