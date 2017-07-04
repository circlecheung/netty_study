package com.zhihao.miao.test.day08;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LuckServerHandler extends SimpleChannelInboundHandler<LuckMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LuckMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println(msg.toString());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("service exception:"+cause.getMessage());
    }
}
