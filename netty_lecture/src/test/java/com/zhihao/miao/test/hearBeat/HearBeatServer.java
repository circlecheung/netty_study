package com.zhihao.miao.test.hearBeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HearBeatServer {
    public static void main(String[] args) {
        NioEventLoopGroup acceptorGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(acceptorGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HearBeatServerInitializer());
            Channel ch = bootstrap.bind(8899).sync().channel();
            System.out.println("Server has started...");
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            acceptorGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
