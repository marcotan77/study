package com.tan.thread.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 一个Future的使用方法
 * @date 2021/7/3 16:45
 **/
public class OneFuture {
    public static void main(String[] args) throws ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(new CallableTack());

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();



    }

    static class CallableTack implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
