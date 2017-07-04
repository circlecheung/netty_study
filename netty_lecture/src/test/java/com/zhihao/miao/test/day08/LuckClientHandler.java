package com.zhihao.miao.test.day08;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LuckClientHandler extends SimpleChannelInboundHandler<LuckMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LuckMessage message) throws Exception {
        System.out.println(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception:"+cause.getMessage());
    }
}