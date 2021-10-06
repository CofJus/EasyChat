package com.cofjus.chat.handler;

import com.cofjus.chat.protocol.res.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author Rui
 * @Date 2021/10/6 20:34
 * @Version 1.0
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponse messageResponse) {
        String fromUserId = messageResponse.getFrom();
        String fromUserName = messageResponse.getFromUsername();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponse
                .getMessage());
    }
}