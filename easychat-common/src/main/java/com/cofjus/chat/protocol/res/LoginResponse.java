package com.cofjus.chat.protocol.res;

import com.cofjus.chat.protocol.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.cofjus.chat.constant.Command.LOGIN_RESPONSE;

/**
 * @Author Rui
 * @Date 2021/10/6 15:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends Message {

    private String id;
    private String username;
    private String password;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
