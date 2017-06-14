package com.zhihao.miao.test.day01;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


public class StringDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 判断是否是String协议
        byte n = "n".getBytes()[0];
        byte p = in.readByte();
        // 把读取的起始位置重置
        in.resetReaderIndex();
        if (n == p) {
            ByteBufToBytes reader = new ByteBufToBytes();
            String msg = new String(reader.read(in));
            User user = buildUser(msg);
            out.add(user);
            //in.release();
        } else {
            ctx.fireChannelRead(in);
        }
    }

    private User buildUser(String msg) {
        User user = new User();
        String[] msgArray = msg.split(";|:");
        user.setName(msgArray[1]);
        user.setAge(Integer.parseInt(msgArray[3]));
        user.setSex(msgArray[5]);
        return user;
    }
}
