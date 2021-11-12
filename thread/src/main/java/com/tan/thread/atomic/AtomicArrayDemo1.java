package com.tan.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 原子数组的使用方法
 * @date 2021/6/4 15:45
 **/
public class AtomicArrayDemo1 {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);

        Thread[] threadIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(decrementer);
            threadIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i].start();
            threadIncrementer[i].start();
        }
        for (int i = 0; i < 100; i++) {

            try {
                threadsDecrementer[i].join();
                threadIncrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0){
                System.out.println("发现错误"+i);
            }
        }
        System.out.println("运行结束");
    }
    
        
}

class Decrementer implements Runnable{
    
    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}

