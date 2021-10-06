package com.cofjus.chat.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Rui
 * @Date 2021/10/6 17:33
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Session {

    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + " " + userName;
    }

}
