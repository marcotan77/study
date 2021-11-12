package com.tan.thread.future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 批量提交任务是，用List来批量接受结果
 * @date 2021/7/3 16:56
 **/
public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ArrayList<Future> futures = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Future<Integer> submit = executorService.submit(new CallableTack());
            futures.add(submit);
        }
        for (int i = 0; i < 20; i++) {
            Future<Integer> future = futures.get(i);
            try {
                Integer integer = future.get();
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

    }

    static class CallableTack implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return new Random().nextInt();
        }
    }
}
