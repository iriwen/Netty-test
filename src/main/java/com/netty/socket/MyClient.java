package com.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by LENOVO on 2018/4/6.
 */
public class MyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup  eventLoop = new NioEventLoopGroup();

        try{
            Bootstrap  bootstrap  = new Bootstrap();
            bootstrap.group(eventLoop).channel(NioSocketChannel.class).
                    handler(new ClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8890).sync();

            //主要原因是客户端强制关闭了连接（没有调用SocketChannel的close方法），服务端还在read事件中，此时读取客户端的信息时会报错。
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoop.shutdownGracefully();
        }
    }
}

