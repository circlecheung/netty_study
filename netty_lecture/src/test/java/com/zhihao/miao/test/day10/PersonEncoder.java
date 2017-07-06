package com.zhihao.miao.test.day10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class PersonEncoder extends MessageToByteEncoder<Person> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {
        String username = msg.getUsername();
        int usernamelength = username.length();
        int age = msg.getAge();

        out.writeInt(0); //标识位
        out.writeInt(usernamelength);
        out.writeBytes(username.getBytes());
        out.writeInt(age);
    }
}
