package com.zhihao.miao.test.day05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

public class HttpServerHandler  extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        StringBuilder responseContent = new StringBuilder("");
        if(msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest)msg;

            String methodname = httpRequest.method().name().toLowerCase();

            if("get".equals(methodname)){
                responseContent.append("get反馈");
                QueryStringDecoder decoderQuery = new QueryStringDecoder(httpRequest.uri());
                Map<String, List<String>> uriAttributes = decoderQuery.parameters();
                for (Map.Entry<String, List<String>> attr: uriAttributes.entrySet()) {
                    for (String attrVal: attr.getValue()) {
                        responseContent.append(" URI: " + attr.getKey() + '=' + attrVal + "，");
                    }
                }
            }

            if("post".equals(methodname)){
                responseContent.append("post反馈");
            }

            ByteBuf content = Unpooled.copiedBuffer(responseContent, CharsetUtil.UTF_8); //向客户端返回的内容

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            ctx.writeAndFlush(response);

            ctx.channel().close();

        }

        if(msg instanceof HttpContent){
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf result = httpContent.content();
            byte[] data = new byte[result.readableBytes()];
            result.readBytes(data);
            String request = new String(data, "utf-8");
            System.out.println(request);
            responseContent.append(request);
        }
    }
}
