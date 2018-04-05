package com.netty.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Set;

/**
 * Created by LENOVO on 2017/6/5.
 */
public class SelectorDemo1 {

    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();
        int[] ports = {8001,8002,8003,8005};
        for(Integer item:ports){
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            ServerSocket serverSocket = serverChannel.socket();
            serverSocket.bind(new InetSocketAddress(item));
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);//在选择器上注册通道并指出自己感兴趣的事件
            System.out.println("正在监听 "+item+ " 端口");
        }
        int addKey ;//准备就绪的channel的数量
        while((addKey=selector.select())>0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for(SelectionKey key : selectionKeys){
                if(key.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel client = ssc.accept();
                    client.configureBlocking(false);// 配置为非阻塞的
                    ByteBuffer bf = ByteBuffer.allocate(1024);
                    bf.put(("(y1384)the concurrent time is : " + new Date()).getBytes());
                    bf.flip();
                    client.write(bf);
                    client.close();
                }
            }
            selectionKeys.clear();
        }
    }
}
