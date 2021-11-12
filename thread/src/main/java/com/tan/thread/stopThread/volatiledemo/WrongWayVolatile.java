package com.tan.thread.stopThread.volatiledemo;

/**
 * @description:
 * @author: Tan.cs
 * @version: 1.0
 * @date: 2021/5/6 13:54
 **/
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wayVolatile);
        thread.start();
        Thread.sleep(5000);
        wayVolatile.canceled = true;
    }
}
