package com.zhihao.miao.test.day10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof Person){
            System.out.println(((Person) msg).getUsername());
            System.out.println(((Person) msg).getAge());
        }

        if(msg instanceof Dog){
            System.out.println(((Dog) msg).getName());
            System.out.println(((Dog) msg).getAge());
        }

        if(msg instanceof Cat){
            System.out.println(((Cat) msg).getName());
            System.out.println(((Cat) msg).getCity());
        }


    }
}
