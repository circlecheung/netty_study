package com.zhihao.miao.test.day10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CatEncoder extends MessageToByteEncoder<Cat> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Cat msg, ByteBuf out) throws Exception {
        String name = msg.getName();
        int namelength = name.length();
        String city = msg.getCity();

        out.writeInt(2); //标识位
        out.writeInt(namelength);
        out.writeBytes(name.getBytes());
        out.writeBytes(city.getBytes());

    }
}
