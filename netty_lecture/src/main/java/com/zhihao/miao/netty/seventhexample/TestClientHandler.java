package com.zhihao.miao.netty.seventhexample;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    //客户端像服务器端发送数据
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);

        MyDataInfo.MyMessage myMessage = null;

        if(0 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.PeopleType).
                    setPeople(MyDataInfo.People.newBuilder().setName("张三").
                            setAddress("上海").setAge(26).build()).build();
        }else if(1 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().setName("旺财")
                            .setAge("2").build()).build();
        }else if(2 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().setName("汤姆")
                            .setCity("上海").build()).build();
        }

        ctx.channel().writeAndFlush(myMessage);


    }
}
