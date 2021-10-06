package com.cofjus.chat;

import com.cofjus.chat.codec.FrameDecoder;
import com.cofjus.chat.codec.MessageDecoder;
import com.cofjus.chat.codec.MessageEncoder;
import com.cofjus.chat.handler.AuthHandler;
import com.cofjus.chat.handler.LoginRequestHandler;
import com.cofjus.chat.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * Netty启动类
 *
 * @Author Rui
 * @Date 2021/10/4 19:30
 * @Version 1.0
 */
public class NettyServer {

    private static final int PORT = 8088;

    public static void main(String... args) {
        // Reactor主从模式
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // Netty启动配置类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.group(boosGroup, workGroup)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                pipeline.addLast(new FrameDecoder());
                pipeline.addLast(new MessageDecoder());
                pipeline.addLast(new LoginRequestHandler());
                pipeline.addLast(new AuthHandler());
                pipeline.addLast(new MessageRequestHandler());
                pipeline.addLast(new MessageEncoder());
            }
        });
        bind(serverBootstrap);
    }

    private static void bind(final ServerBootstrap serverBootstrap) {
        serverBootstrap.bind(PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + PORT + "]绑定成功!");
            } else {
                System.err.println("端口[" + PORT + "]绑定失败!");
            }
        });
    }
}
