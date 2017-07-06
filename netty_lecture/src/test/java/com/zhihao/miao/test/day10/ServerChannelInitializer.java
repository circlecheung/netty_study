package com.zhihao.miao.test.day10;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //解析handler
        pipeline.addLast(new ServlerDecoder());
        pipeline.addLast(new TestServerHandler());
    }
}
