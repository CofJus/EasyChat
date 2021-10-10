package com.cofjus.chat.response;

import com.cofjus.chat.request.LoginRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author Rui
 * @Date 2021/10/7 17:07
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class LoginResponse {
    private boolean success;
    private String sessionId;
    private Long userId;
    private String username;

    public LoginResponse(LoginRequest loginRequest) {
        this.userId = loginRequest.getUserId();
        this.username = loginRequest.getUserName();
    }
}
