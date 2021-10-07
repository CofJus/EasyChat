package com.cofjus.chat.response;

import com.cofjus.chat.request.RegisterRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Rui
 * @Date 2021/10/7 12:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class RegisterResponse {
    private Long userId;
    private String userName;

    public RegisterResponse(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public RegisterResponse(RegisterRequest request) {
        this.userId = request.getUserId();
        this.userName = request.getUserName();
    }
}
