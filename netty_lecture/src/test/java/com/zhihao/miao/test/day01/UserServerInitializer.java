package com.zhihao.miao.test.day01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class UserServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new UserDecoder());
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new BusinessHandler());
    }
}
