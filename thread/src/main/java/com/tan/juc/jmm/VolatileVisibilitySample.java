package com.tan.juc.jmm;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/10/7 14:06
 **/
public class VolatileVisibilitySample {
    private volatile boolean initFlag = false;

    public void save() {
        this.initFlag = true;
        String threadName = Thread.currentThread().getName();
        System.out.println("线程："+threadName+"修改共享变量initFlag");
    }

    public void load() {
        String threadName = Thread.currentThread().getName();

        while (!initFlag){
            // 线程在此处跑空，等待initFlag状态改变
        }
        System.out.println("线程："+threadName+ "当前线程嗅探到initFlag状态的改变");
    }

    public static void main(String[] args) {
        VolatileVisibilitySample sample = new VolatileVisibilitySample();

        Thread threadA = new Thread(() -> {
            sample.save();
        },"ThreadA");
        Thread threadB = new Thread(() -> {
            sample.load();
        },"ThreadB");

        threadB.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();

    }
}
