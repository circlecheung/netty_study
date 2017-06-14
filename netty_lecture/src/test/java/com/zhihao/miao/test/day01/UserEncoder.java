package com.zhihao.miao.test.day01;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class UserEncoder extends MessageToByteEncoder<User>  {

    @Override
    protected void encode(ChannelHandlerContext ctx, User msg, ByteBuf out) throws Exception {
        out.writeBytes(ByteObjConverter.ObjectToByte(msg));
    }
}
