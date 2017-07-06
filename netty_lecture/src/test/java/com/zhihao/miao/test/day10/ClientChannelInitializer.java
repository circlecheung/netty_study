package com.zhihao.miao.test.day10;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Random;


public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        int randomInt = new Random().nextInt(3);

        if(0 == randomInt){
            pipeline.addLast(new PersonEncoder());
            Person person = new Person();
            person.setUsername("zhihao");
            person.setAge(27);
            pipeline.addLast(new TestClientHandler(person));
        }

        if(1 == randomInt){
            pipeline.addLast(new DogEncoder());
            Dog dog = new Dog();
            dog.setName("wangcai");
            dog.setAge("2");
            pipeline.addLast(new TestClientHandler(dog));
        }

        if(2 == randomInt){
            pipeline.addLast(new CatEncoder());
            Cat cat = new Cat();
            cat.setName("maomi");
            cat.setCity("shanghai");
            pipeline.addLast(new TestClientHandler(cat));
        }
    }
}
