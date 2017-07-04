package com.zhihao.miao.test.hearBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import static com.zhihao.miao.test.hearBeat.MyDataInfo.HearBeat.newBuilder;

//处理器Handler，这边继承了ChannelInboundHandlerAdapter（SimpleChannelInboundHandler也是继承
// 了ChannelInboundHandlerAdapter），关于这些类下面会具体研究解释。
public class ClientHeartbeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- Server is active ---");
    }

    //当服务器端断开链接时会执行这个回调方法，然后去重新连接服务器端
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- Server is inactive ---");

        // 10s 之后尝试重新连接服务器
        System.out.println("10s 之后尝试重新连接服务器...");
        Thread.sleep(10 * 1000);
        HearBeatClient.doConnect();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //不管是读事件空闲还是写事件空闲都向服务器发送心跳包
            sendHeartbeatPacket(ctx);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("连接出现异常");
    }

    //发送心跳包
    private void sendHeartbeatPacket(ChannelHandlerContext ctx) {
        MyDataInfo.HearBeat.Builder builder = newBuilder();
        builder.setHearBeatType(MyDataInfo.HearBeat.HearBeatType.HEARTBEAT);
        MyDataInfo.HearBeat beat = builder.build();
        ctx.writeAndFlush(beat);
    }
}
