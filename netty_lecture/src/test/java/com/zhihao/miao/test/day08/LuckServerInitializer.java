package com.zhihao.miao.test.day08;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class LuckServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new LuckEncoder());
        pipeline.addLast(new LuckDecoder());
        // 添加逻辑控制层
        pipeline.addLast(new LuckServerHandler());

    }
}
