package com.tan.thread;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/30 11:15
 **/
public final class Location2 {

    public static void main(String[] args) {


        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                if (i == 5){
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程");
                i++;
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程");

    }
}
