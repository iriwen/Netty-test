package com.concurrent.futureTest;

import java.util.concurrent.CompletableFuture;


public class FutureTest1 {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> fu = CompletableFuture.supplyAsync(() -> {
            System.out.println("start execute .... ");
            try {
                int a = 1;
                int b = 2;
                //Thread.sleep(3000);
                int c = a + b;
                System.out.println("completableFuture print c : " + c);
                return c + " execute finished";
            } catch (Exception e) {
                e.printStackTrace();
                return "happen exception";
            }
        });
        fu.thenApplyAsync(x -> {
            System.out.println("then apply " + x);
            return "result ok";
        });
        Thread.sleep(5000);
        System.out.println("get the compeltableFuture value is " + fu.get());
    }
}
