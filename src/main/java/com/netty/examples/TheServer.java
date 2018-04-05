package com.netty.examples;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by LENOVO on 2017/6/4.
 */
public class TheServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup  bossGroup = new NioEventLoopGroup();
        EventLoopGroup  workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = sb.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally{
             bossGroup.shutdownGracefully();
             workerGroup.shutdownGracefully();
        }
    }
}
