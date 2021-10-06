package com.cofjus.chat.protocol.req;

import com.cofjus.chat.protocol.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.cofjus.chat.constant.Command.MESSAGE_REQUEST;

/**
 * @Author Rui
 * @Date 2021/10/6 15:58
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MessageRequest extends Message {

    private String to;
    private String message;

    public MessageRequest(String to, String message) {
        this.to = to;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
