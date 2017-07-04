package com.zhihao.miao.test.hearBeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Random;

import static com.zhihao.miao.test.hearBeat.MyDataInfo.HearBeat.newBuilder;

public class HearBeatClient {
    private static Channel ch;
    private static Bootstrap bootstrap;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap();
            bootstrap
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientHearBeatInitializer());

            // 连接服务器
            doConnect();

            // 模拟不定时发送向服务器发送数据的过程
            Random random = new Random();
            while (true) {
                int num = random.nextInt(21);
                Thread.sleep(num * 1000);
                MyDataInfo.HearBeat.Builder builder = newBuilder();
                builder.setHearBeatType(MyDataInfo.HearBeat.HearBeatType.DATA);
                builder.setData("我是数据包（非心跳包） " + num);
                MyDataInfo.HearBeat hearBeat = builder.build();
                ch.writeAndFlush(hearBeat);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    //抽取出该方法 (断线重连时使用)
    public static void doConnect() throws InterruptedException {
        ch = bootstrap.connect("127.0.0.1", 8899).sync().channel();
    }
}
