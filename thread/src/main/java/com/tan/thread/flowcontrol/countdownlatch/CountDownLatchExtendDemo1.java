package com.tan.thread.flowcontrol.countdownlatch;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/2 16:19
 **/
public class CountDownLatchExtendDemo1 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(new String[]{"A", "B", "C", "D", "A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2"});
        int cpu = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cpu * 2 + 1);
        int count = 2; // 一个线程处理2条数据
        int threadsSize = (list.size() / count) + 1;
        CopyOnWriteArrayList<String> newlist = null; // 存放每个线程的执行数据
        // 创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(threadsSize);
        try {
            // 循环创建线程
            for (int i = 0; i < threadsSize; i++) {
                // 计算每个线程执行的数据
                if ((i + 1) == threadsSize) {
                    int startIndex = (i * count);
                    int endIndex = list.size();
                    newlist = new CopyOnWriteArrayList<String>();
                    for (int q = startIndex; q < endIndex; q++) {
                        newlist.add(list.get(q));
                    }
                } else {
                    int startIndex = (i * count);
                    int endIndex = (i + 1) * count;
                    newlist = new CopyOnWriteArrayList<String>();
                    for (int q = startIndex; q < endIndex; q++) {
                        newlist.add(list.get(q));
                    }
                }
                MyWorkThread mythead = new MyWorkThread().getInstance(newlist,
                        begin, end);
                executor.execute(mythead);
            }
            begin.countDown();
            end.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static class MyWorkThread implements Runnable {

        private CopyOnWriteArrayList<String> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        public MyWorkThread getInstance(CopyOnWriteArrayList<String> list, CountDownLatch begin, CountDownLatch end) {
            MyWorkThread myWorkThread = new MyWorkThread();
            myWorkThread.list = list;
            myWorkThread.begin = begin;
            myWorkThread.end = end;
            return myWorkThread;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(Thread.currentThread().getName()+"-----"+list.get(i));
                }
                // 执行完让线程直接进入等待
                begin.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }

        }
    }
}
