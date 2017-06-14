package com.zhihao.miao.test.day01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ClientInitHandler extends ChannelInboundHandlerAdapter {
    private User user;
    public ClientInitHandler(User user){
        this.user = user;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(user);
    }
}
