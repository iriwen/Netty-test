package com.netty.nio;

import java.nio.IntBuffer;

/**
 * Created by LENOVO on 2017/6/5.
 */
public class BufferTest1 {
    public static void main(String[] args) {
        IntBuffer intBuf = IntBuffer.allocate(10);
        System.out.println("position : " + intBuf.position());
        int[] temp={2,5,6,1};
        intBuf.put(temp);
        System.out.println("position : "+ intBuf.position());
    }

 }