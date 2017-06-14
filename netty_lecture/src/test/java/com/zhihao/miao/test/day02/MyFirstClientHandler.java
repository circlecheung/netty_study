package com.zhihao.miao.test.day02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;


public class MyFirstClientHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端："+msg);
    }

    //当服务器端与客户端进行建立的时候会触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自与客户端的问题!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
