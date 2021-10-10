package com.cofjus.chat.response;

import com.cofjus.chat.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    public RegisterResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }
}
