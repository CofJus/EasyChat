package com.cofjus.chat.handler;

import com.cofjus.chat.protocol.res.LoginResponse;
import com.cofjus.chat.session.Session;
import com.cofjus.chat.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author Rui
 * @Date 2021/10/6 20:12
 * @Version 1.0
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse loginResponse) {
        String userId = loginResponse.getId();
        String userName = loginResponse.getUsername();
        if (loginResponse.isSuccess()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponse.getId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + loginResponse.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
