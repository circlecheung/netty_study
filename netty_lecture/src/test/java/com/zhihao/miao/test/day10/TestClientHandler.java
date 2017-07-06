package com.zhihao.miao.test.day10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class TestClientHandler extends ChannelInboundHandlerAdapter {

    private Person person;

    private Cat cat;

    private Dog dog;

    public TestClientHandler(Person person){
        this.person = person;
    }

    public TestClientHandler(Dog dog){
        this.dog = dog;
    }

    public TestClientHandler(Cat cat){
        this.cat =cat;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if(person != null){
            ctx.channel().writeAndFlush(person);
        }

        if(dog != null){
            ctx.channel().writeAndFlush(dog);
        }

        if(cat != null){
            ctx.channel().writeAndFlush(cat);
        }
    }
}
