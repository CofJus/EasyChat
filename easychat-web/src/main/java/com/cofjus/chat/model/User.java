package com.cofjus.chat.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author Rui
 * @Date 2021/10/7 9:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private Long userId;
    private String userName;
    private String password;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User(Long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}