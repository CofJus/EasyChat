package com.cofjus.chat;

import com.cofjus.chat.codec.FrameDecoder;
import com.cofjus.chat.codec.MessageDecoder;
import com.cofjus.chat.codec.MessageEncoder;
import com.cofjus.chat.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

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
        try {
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
                    pipeline.addLast(new FrameDecoder());
                    pipeline.addLast(new MessageDecoder());
                    pipeline.addLast(new LoggingHandler());
                    pipeline.addLast(new MessageRequestHandler());
                    pipeline.addLast(new MessageEncoder());
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 端口[" + PORT + "]绑定成功!");
                } else {
                    System.out.println("端口[" + PORT + "]绑定失败!");
                }
            }).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
