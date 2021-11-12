package com.tan.thread.flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/2 15:25
 **/
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3,true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task());
        }
        executorService.shutdown();
    }
    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"释放了许可证");
            semaphore.release();
        }
    }
}
