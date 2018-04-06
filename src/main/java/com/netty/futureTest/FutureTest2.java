package com.netty.futureTest;

import java.util.concurrent.CompletableFuture;

/**
 * Created by LENOVO on 2017/6/26.
 */
public class FutureTest2 {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> 15);
        CompletableFuture<String> fuTask = task1.thenCombine(task2, (x, y) -> {
            return x + y;
        }).thenApplyAsync(item -> {
            if (item > 24) {
                try {
                    return "" + CompletableFuture.supplyAsync(() -> "hello").thenCombine(CompletableFuture.
                            supplyAsync(() -> "world"), (a, b) -> a + b).get();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "hehe...";
                }
            } else {
                return "hehe...";
            }
        });
        System.out.println("value is :" + fuTask.get());

    }
}
