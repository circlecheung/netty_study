package com.zhihao.miao.test.day07;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Person person = new Person();
        person.setAge(27);
        person.setName("miaozhihao");
        person.setSex("boy");
        ctx.channel().writeAndFlush(person);
    }
}
