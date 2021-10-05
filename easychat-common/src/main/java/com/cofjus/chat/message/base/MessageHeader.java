package com.cofjus.chat.message.base;

import lombok.Data;

/**
 * @Author Rui
 * @Date 2021/10/5 9:33
 * @Version 1.0
 */
@Data
public class MessageHeader {
    private int version;
    private int opCode;
    private long streamId;
}
