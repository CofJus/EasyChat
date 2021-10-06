package com.cofjus.chat.codec;

import com.cofjus.chat.protocol.Message;
import com.cofjus.chat.protocol.req.LoginRequest;
import com.cofjus.chat.protocol.req.MessageRequest;
import com.cofjus.chat.protocol.res.LoginResponse;
import com.cofjus.chat.protocol.res.MessageResponse;
import com.cofjus.chat.serialize.*;
import com.cofjus.chat.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.cofjus.chat.constant.Command.*;

/**
 * 编/解码器
 * @Author Rui
 * @Date 2021/10/6 16:24
 * @Version 1.0
 */
public class MessageCodec {

    public static final MessageCodec INSTANCE = new MessageCodec();
    public static final int MAGIC_NUMBER = 0x12345678;
    private final Map<Byte, Class<? extends Message>> messageTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    public MessageCodec() {
        messageTypeMap = new HashMap<>();
        serializerMap = new HashMap<>();
        initMessageTypeMap();
        initSerializerMap();
    }

    public void encode(ByteBuf byteBuf, Message message) {
        // 1. 序列化 java 对象
        Serializer jsonSerializer = new JsonSerializer();
        byte[] bytes = jsonSerializer.serialize(message);

        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(message.getVersion());
        byteBuf.writeByte(jsonSerializer.getAlgorithm());
        byteBuf.writeByte(message.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Message decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        // 负载
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        // 获取消息类型和序列化算法
        Class<? extends Message> requestType = getRequestType(command);
        Serializer serializer = getSerializerByAlgorithm(serializeAlgorithm);

        // 反序列化
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private void initMessageTypeMap() {
        messageTypeMap.put(LOGIN_REQUEST, LoginRequest.class);
        messageTypeMap.put(LOGIN_RESPONSE, LoginResponse.class);
        messageTypeMap.put(MESSAGE_REQUEST, MessageRequest.class);
        messageTypeMap.put(MESSAGE_RESPONSE, MessageResponse.class);
    }

    private void initSerializerMap() {
        Serializer jsonSerializer = new JsonSerializer();
        serializerMap.put(jsonSerializer.getAlgorithm(), jsonSerializer);
    }

    private Serializer getSerializerByAlgorithm(Byte algorithm) {
        return serializerMap.get(algorithm);
    }

    private Class<? extends Message> getRequestType(Byte command) {
        return messageTypeMap.get(command);
    }
}
