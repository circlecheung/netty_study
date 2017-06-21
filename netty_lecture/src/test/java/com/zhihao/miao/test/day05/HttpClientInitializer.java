package com.zhihao.miao.test.day05;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

public class HttpClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //客户端要对client进行编码，对服务器进行解码,下面二句可以替换为
        //pipeline.addLast(new HttpRequestEncoder());
        //pipeline.addLast(new HttpResponseDecoder());

        pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new HttpClientHandler());
    }
}
