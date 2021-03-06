package com.cofjus.chat;

import com.cofjus.chat.codec.FrameDecoder;
import com.cofjus.chat.codec.MessageDecoder;
import com.cofjus.chat.codec.MessageEncoder;
import com.cofjus.chat.handler.LoginResponseHandler;
import com.cofjus.chat.handler.MessageResponseHandler;
import com.cofjus.chat.protocol.req.LoginRequest;
import com.cofjus.chat.protocol.req.MessageRequest;
import com.cofjus.chat.utils.SessionUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @Author Rui
 * @Date 2021/10/6 20:12
 * @Version 1.0
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8088;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {

                        ch.pipeline().addLast(new FrameDecoder());
                        ch.pipeline().addLast(new MessageDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new MessageEncoder());
                        // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": ??????????????????????????????????????????");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("???????????????????????????????????????");
            } else {
                // ???????????????
                int order = (MAX_RETRY - retry) + 1;
                // ?????????????????????
                int delay = 1 << order;
                System.err.println(new Date() + ": ??????????????????" + order + "???????????????");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequest loginRequest = new LoginRequest();

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("?????????????????????: ");
                    String username = sc.nextLine();
                    loginRequest.setUsername(username);
                    // ?????????????????????
                    loginRequest.setPassword("pwd");

                    // ?????????????????????
                    channel.writeAndFlush(loginRequest);
                    waitForLoginResponse();
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    channel.writeAndFlush(new MessageRequest(toUserId, message));
                }
            }
        });
        singleThreadPool.shutdown();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
