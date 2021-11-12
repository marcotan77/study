package com.tan.thread.threadPool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/5/20 15:06
 **/
public class threadPool {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 30; i++) {
            MyRunnable myRunnable = new MyRunnable(""+i);
            threadPoolExecutor.execute(myRunnable);
        }
    }

    static class MyRunnable implements Runnable{

        private String command;

        public MyRunnable(String command) {
            this.command = command;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"Start Time="+new Date());
            processCommand();
            System.out.println(Thread.currentThread().getName()+"End Time="+new Date());
        }

        private void processCommand() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
