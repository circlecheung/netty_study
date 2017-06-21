package com.zhihao.miao.test.day05;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //服务端要对Response进行编码，对Request进行解码
        //pipeline.addLast(new HttpResponseEncoder());
        //pipeline.addLast(new HttpRequestDecoder());

        pipeline.addLast(new HttpServerCodec());

        pipeline.addLast(new HttpServerHandler());
    }
}
