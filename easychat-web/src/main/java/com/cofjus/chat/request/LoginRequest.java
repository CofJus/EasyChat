package com.cofjus.chat.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Rui
 * @Date 2021/10/7 17:07
 * @Version 1.0
 */
@Data
public class LoginRequest {
    private Long userId;
    private String userName;
    private String password;
}
