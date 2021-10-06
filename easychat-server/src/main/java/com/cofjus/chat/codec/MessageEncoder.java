package com.cofjus.chat.codec;

import com.cofjus.chat.protocol.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author Rui
 * @Date 2021/10/6 16:19
 * @Version 1.0
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) {
        MessageCodec.INSTANCE.encode(out, msg);
    }
}
