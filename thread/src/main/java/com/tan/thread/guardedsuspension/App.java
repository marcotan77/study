package com.tan.thread.guardedsuspension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/30 15:33
 **/
public class App {
    public static void main(String[] args) throws InterruptedException {
        GuardedQueue guardedQueue = new GuardedQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            System.out.println(guardedQueue.get());

            }
        );
        Thread.sleep(2000);
        executorService.execute(() -> {
                    guardedQueue.put(20);
                }
        );
        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        new StringBuffer();
        new Throwable();
    }
}
