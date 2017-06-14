package com.zhihao.miao.netty.fourthexample;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class MyServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //处理空闲状态事件的处理器
        pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}
