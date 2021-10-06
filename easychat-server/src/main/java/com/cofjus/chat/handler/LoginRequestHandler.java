package com.cofjus.chat.handler;

import com.cofjus.chat.protocol.req.LoginRequest;
import com.cofjus.chat.protocol.res.LoginResponse;
import com.cofjus.chat.session.Session;
import com.cofjus.chat.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author Rui
 * @Date 2021/10/6 17:29
 * @Version 1.0
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(msg.getUsername());
        loginResponse.setVersion(msg.getVersion());
        if (isValid(loginResponse)) {
            loginResponse.setSuccess(true);
            String id = randomUserId();
            loginResponse.setId(id);
            System.out.println("[" + msg.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(id, msg.getUsername()), ctx.channel());
        } else {
            loginResponse.setReason("账号密码校验失败");
            loginResponse.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.channel().writeAndFlush(loginResponse);
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private boolean isValid(LoginResponse loginResponse) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
