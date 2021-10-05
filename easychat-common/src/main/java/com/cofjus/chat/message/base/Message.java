package com.cofjus.chat.message.base;

import lombok.Data;

/**
 * @Author Rui
 * @Date 2021/10/5 9:40
 * @Version 1.0
 */
@Data
public abstract class Message<T extends MessageBody> {
    private MessageHeader messageHeader;
    private T messageBody;

    public T getMessageBody() {
        return messageBody;
    }


}
