package com.netty.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Created by LENOVO on 2018/4/6.
 */
//泛型的意思声明客户端和服务器端传输的数据类型就是字符串
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    /*
      @param  ChannelHandlerContext  很重要的一个对象表示的是netty上下文对象（可以获取远程的地址和连接对象）
      msg表示接受的信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
         System.out.println(ctx.channel().remoteAddress() + ":" + msg);
         ctx.channel().writeAndFlush("from server" + UUID.randomUUID());
    }
     //当出现了异常执行，通常采用关闭连接的方式
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
