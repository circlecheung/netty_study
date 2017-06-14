package com.zhihao.miao.netty.seventhexample;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();

        if(dataType == MyDataInfo.MyMessage.DataType.PeopleType){
            MyDataInfo.People people = msg.getPeople();

            System.out.println(people.getName());
            System.out.println(people.getAge());
            System.out.println(people.getAddress());
        }else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();

            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        }else if(dataType == MyDataInfo.MyMessage.DataType.CatType){
            MyDataInfo.Cat cat = msg.getCat();

            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }
    }
}
