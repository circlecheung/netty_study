package com.zhihao.miao.test.hearBeat;

import com.zhihao.miao.test.hearBeat.MyDataInfo.HearBeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

//处理器Handler，这边继承了ChannelInboundHandlerAdapter（SimpleChannelInboundHandler也是继承
// 了ChannelInboundHandlerAdapter），关于这些类下面会具体研究解释。
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {
    // 心跳丢失计数器
    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端存活");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已经挂了");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 判断接收到的包类型
        if (msg instanceof HearBeat) {
            HearBeat packet = (HearBeat) msg;

            switch (packet.getHearBeatType()) {
                case HEARTBEAT:
                    handleHeartbreat(ctx, packet);
                    break;

                case DATA:
                    handleData(ctx, packet);
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            // 空闲4s之后触发 (心跳包丢失)
            if (counter >= 3) {
                // 连续丢失3个心跳包 (断开连接)
                ctx.channel().close().sync();
                System.out.println("已与Client断开连接");
            } else {
                counter+=1;
                System.out.println("丢失了第 " + counter + " 个心跳包");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("连接出现异常");
    }

    //处理心跳包
    private void handleHeartbreat(ChannelHandlerContext ctx, HearBeat hearBeat) {
        // 将心跳丢失计数器置为0
        counter = 0;
        System.out.println("收到心跳包");
        ReferenceCountUtil.release(hearBeat);
    }

    //处理数据包
    private void handleData(ChannelHandlerContext ctx, HearBeat hearBeat) {
        //将心跳丢失计数器置为0
        counter = 0;
        String data = hearBeat.getData();
        System.out.println(data);
        ReferenceCountUtil.release(hearBeat);
    }

}
