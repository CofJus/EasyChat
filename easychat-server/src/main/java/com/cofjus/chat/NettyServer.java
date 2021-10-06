package com.cofjus.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
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

        // 日志
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));

        serverBootstrap.group(boosGroup, workGroup);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();

            }
        });
        bind(serverBootstrap, PORT);
    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.out.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
