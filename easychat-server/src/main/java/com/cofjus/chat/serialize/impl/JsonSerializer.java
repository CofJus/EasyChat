package com.cofjus.chat.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.cofjus.chat.serialize.Serializer;
import com.cofjus.chat.constant.SerializeAlgorithm;
/**
 * @Author Rui
 * @Date 2021/10/6 15:22
 * @Version 1.0
 */
public class JsonSerializer implements Serializer {

    @Override
    public Byte getAlgorithm() {
        return SerializeAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
