package com.tan.thread.flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 工厂中 质检 5个工人检查 ，所有人都认为通过才通过
 * @date 2021/7/2 14:16
 **/
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No" + no + "完成检查");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("等待5个人检查完。。。");
        latch.await();
        System.out.println("所有人都完成工作");
    }
}
