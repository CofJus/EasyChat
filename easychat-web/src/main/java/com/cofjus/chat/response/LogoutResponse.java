package com.cofjus.chat.response;

import lombok.Data;

/**
 * @Author Rui
 * @Date 2021/10/10 17:47
 * @Version 1.0
 */
@Data
public class LogoutResponse {

    private boolean success;

    private Long userId;
}
