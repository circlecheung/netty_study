package com.zhihao.miao.test.day10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class DogEncoder extends MessageToByteEncoder<Dog> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Dog msg, ByteBuf out) throws Exception {

        String name = msg.getName();
        int namelength = name.length();
        String age = msg.getAge();

        out.writeInt(1); //标识位
        out.writeInt(namelength);
        out.writeBytes(name.getBytes());
        out.writeBytes(age.getBytes());


    }
}
