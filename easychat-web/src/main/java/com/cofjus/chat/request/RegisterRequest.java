package com.cofjus.chat.request;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author Rui
 * @Date 2021/10/7 12:20
 * @Version 1.0
 */
@Data
public class RegisterRequest {
    private Long userId;
    private String userName;
    private String password;
}
