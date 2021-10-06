package com.cofjus.chat.protocol.req;

import lombok.Data;
import com.cofjus.chat.protocol.Message;
import lombok.EqualsAndHashCode;

import static com.cofjus.chat.constant.Command.LOGIN_REQUEST;

/**
 * @Author Rui
 * @Date 2021/10/6 15:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest extends Message {
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
