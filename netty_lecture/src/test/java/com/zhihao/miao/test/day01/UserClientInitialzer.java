package com.zhihao.miao.test.day01;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Random;


public class UserClientInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        int i = new Random().nextInt(2);
        ChannelPipeline pipeline = ch.pipeline();
        if(i == 0){
            pipeline.addLast(new UserEncoder());
            User user = new User();
            user.setName("张三");
            user.setSex("男");
            user.setAge(28);
            pipeline.addLast(new ClientInitHandler(user));
        }
        else if(i == 1){
            pipeline.addLast(new StringEncoder());
            User user = new User();
            user.setName("李娜");
            user.setSex("女");
            user.setAge(26);
            pipeline.addLast(new ClientInitHandler(user));

        }
    }
}
