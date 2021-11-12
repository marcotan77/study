package com.tan.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tan.cs
 * @version 1.0   破坏不可抢占条件
 * @description 死锁情况(使用lock)
 * @date 2021/6/30 14:53
 **/
public class DeadLock3 {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Lock1());
        Thread t2 = new Thread(new Lock2());
        t1.start();
        t2.start();
    }

    static class Lock1 implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    if (lock1.tryLock(1, TimeUnit.MILLISECONDS)){
                        System.out.println("Lock1 lock obj1");
                        if (lock2.tryLock(1,TimeUnit.MILLISECONDS)){
                            System.out.println("Lock1 lock obj2");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    static class Lock2 implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    if (lock2.tryLock(1, TimeUnit.MILLISECONDS)){
                        System.out.println("Lock2 lock obj2");
                        if (lock1.tryLock(1,TimeUnit.MILLISECONDS)){
                            System.out.println("Lock2 lock obj1");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                lock1.unlock();
                lock2.unlock();
            }
        }
    }

}
