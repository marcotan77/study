package com.tan.thread.sixStates;

/**
 * @description:
 * @author: Tan.cs
 * @version: 1.0
 * @date: 2021/5/6 15:24
 **/
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable,"thread1");
        thread1.start();
        Thread thread2 = new Thread(runnable,"thread2");
        thread2.start();
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            Thread.sleep(5000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
