package com.cofjus.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Author Rui
 * @Date 2021/10/7 9:46
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.cofjus.chat.dao")
@ComponentScan({"com.cofjus.chat.dao", "com.cofjus.chat.controller", "com.cofjus.chat.service"})
public class EasyChatApplication {
    public static void main(String... args) {
        SpringApplication.run(EasyChatApplication.class);
    }
}
