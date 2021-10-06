package com.cofjus.chat.attribute;

import com.cofjus.chat.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author Rui
 * @Date 2021/10/6 15:43
 * @Version 1.0
 */
public class Attribute {
    public static AttributeKey<Session> session = AttributeKey.newInstance("session");
}