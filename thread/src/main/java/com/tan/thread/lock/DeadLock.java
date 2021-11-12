package com.tan.thread.lock;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 死锁情况
 * @date 2021/6/30 14:53
 **/
public class DeadLock {

    private static String obj1 = "obj1";
    private static String obj2 = "obj2";

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
                    synchronized (DeadLock.obj1) {
                        System.out.println("Lock1 lock obj1");
                        Thread.sleep(3000);
                        synchronized (DeadLock.obj2){
                            System.out.println("Lock1 lock obj2");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Lock2 implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (DeadLock.obj2) {
                        System.out.println("Lock2 lock obj2");
                        Thread.sleep(3000);
                        synchronized (DeadLock.obj1){
                            System.out.println("Lock2 lock obj1");
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
