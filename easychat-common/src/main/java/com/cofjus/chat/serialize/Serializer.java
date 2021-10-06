package com.cofjus.chat.serialize;

/**
 * @Author Rui
 * @Date 2021/10/6 15:22
 * @Version 1.0
 */
public interface Serializer {

    Byte getAlgorithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
