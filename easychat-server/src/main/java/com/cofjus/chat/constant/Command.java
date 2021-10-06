package com.cofjus.chat.constant;

import lombok.Value;

/**
 * @Author Rui
 * @Date 2021/10/6 13:59
 * @Version 1.0
 */
public class Command {

    public static final Byte LOGIN_REQUEST = 1;

    public static final Byte LOGIN_RESPONSE = 2;

    public static final Byte MESSAGE_REQUEST = 3;

    public static final Byte MESSAGE_RESPONSE = 4;
}
