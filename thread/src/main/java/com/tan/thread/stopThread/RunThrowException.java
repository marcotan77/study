package com.tan.thread.stopThread;

/**
 * @description: 传递中断
 * @author: Tan.cs
 * @version: 1.0
 * @date: 2021/5/6 13:22
 **/
public class RunThrowException {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void aVppppoid() throws Exception {
        throw new Exception();
    }




}
