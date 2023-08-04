package com.tan.thread;

/**
 * @author tanchusheng
 * @date 2023/8/4 9:18
 */
public class TwoThreadPrint {

    private static int count;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PrintNumTaskRunner(),"偶数").start();
        Thread.sleep(1);
        new Thread(new PrintNumTaskRunner(),"奇数").start();
    }


    private static class PrintNumTaskRunner implements Runnable {
        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notifyAll();

                    try {
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

