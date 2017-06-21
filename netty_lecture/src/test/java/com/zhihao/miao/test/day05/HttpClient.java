package com.zhihao.miao.test.day05;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import java.net.URI;

public class HttpClient {

        public static void main(String[] args) throws Exception{
            EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

            try{
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                        .handler(new HttpClientInitializer());

                ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync();

                URI uriSimple = new URI("http://127.0.0.1:8000/update");

                String params="a=b&c=d";

                DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                        uriSimple.toASCIIString(),Unpooled.wrappedBuffer(params.getBytes()));

                request.headers().set(HttpHeaders.Names.HOST, "localhost");
                request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());

                channelFuture.channel().writeAndFlush(request);

                channelFuture.channel().closeFuture().sync();
            }finally {
                eventLoopGroup.shutdownGracefully();
            }
        }
}
