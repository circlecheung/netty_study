package com.zhihao.miao.test.day07;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {
        System.out.println(msg.getAge());
        System.out.println(msg.getName());
        System.out.println(msg.getSex());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getMessage();
    }
}
