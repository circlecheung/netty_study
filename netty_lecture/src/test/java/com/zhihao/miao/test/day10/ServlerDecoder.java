package com.zhihao.miao.test.day10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


public class ServlerDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int flag = in.readInt();

        if(flag == 0){
            int usernamelength = in.readInt();

             byte[] usernamebys = new byte[usernamelength];
             in.readBytes(usernamebys);

             String username = new String(usernamebys);

             int age = in.readInt();

             Person pserson = new Person();
             pserson.setUsername(username);
             pserson.setAge(age);

             out.add(pserson);


        }
        if(flag ==1){
            int namelength =in.readInt();

            byte[] namebys = new byte[namelength];
            in.readBytes(namebys);

            String name = new String(namebys);

            byte[] agebys = new byte[in.readableBytes()];
            in.readBytes(agebys);

            String age = new String(agebys);

            Dog dog = new Dog();
            dog.setName(name);
            dog.setAge(age);

            out.add(dog);
        }
        if(flag ==2){
            int namelength = in.readInt();

            byte[] nameByte = new byte[namelength];
            in.readBytes(nameByte);

            String name = new String(nameByte);

            byte[] citybys = new byte[in.readableBytes()];
            in.readBytes(citybys);

            String city = new String(citybys);

            Cat cat = new Cat();
            cat.setName(name);
            cat.setCity(city);

            out.add(cat);
        }


    }
}
