package com.cofjus.chat.handler;

import com.cofjus.chat.protocol.req.MessageRequest;
import com.cofjus.chat.protocol.res.MessageResponse;
import com.cofjus.chat.session.Session;
import com.cofjus.chat.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author Rui
 * @Date 2021/10/6 17:29
 * @Version 1.0
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setFrom(session.getUserId());
        messageResponse.setFromUsername(session.getUserName());
        messageResponse.setMessage(msg.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(msg.getTo());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponse);
        } else {
            System.err.println("[" + msg.getTo() + "] 不在线，发送失败!");
        }
    }
}
