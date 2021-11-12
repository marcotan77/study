package com.tan.thread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/5/24 15:12
 **/
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownTask());
        }

        Thread.sleep(1500);
    }

    static class ShutDownTask implements Runnable{

        @Override
        public void run() {

        }
    }
}
