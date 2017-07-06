package com.zhihao.miao.test.day10;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MultiClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer());

        // Start the connection attempt.
        Channel ch = b.connect("127.0.0.1", 8899).sync().channel();

        ch.flush();
    }
}
