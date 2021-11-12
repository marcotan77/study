package com.tan.thread.sixStates;

/**
 * @description: 展示线程的NEW RUNNABLE TERMINATED状态， 即使是正在运行，也是Runnable状态 而不是running
 * @author: Tan.cs
 * @version: 1.0
 * @date: 2021/5/6 15:17
 **/
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
