package com.netty.examples;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by LENOVO on 2017/6/6.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println("class type is :"+msg.getClass());

        System.out.println(ctx.channel().remoteAddress());
        if(msg instanceof HttpRequest){
            HttpRequest  httprequest = (HttpRequest) msg;

            System.out.println("请求方法名字： " + httprequest.method().name());
            URI  uri= new URI(httprequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求 favicon icon");
                return;
            }
            ByteBuf bf = Unpooled.copiedBuffer("hello world :y13184" , CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,bf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,bf.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
