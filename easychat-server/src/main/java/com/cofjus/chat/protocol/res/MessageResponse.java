package com.cofjus.chat.protocol.res;

import com.cofjus.chat.protocol.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Rui
 * @Date 2021/10/6 15:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponse extends Message {

    private String from;
    private String fromUsername;
    private String message;

    @Override
    public Byte getCommand() {
        return null;
    }
}
