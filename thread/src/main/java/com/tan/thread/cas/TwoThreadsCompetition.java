package com.tan.thread.cas;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 模拟CAS操作，等价代码
 * @date 2021/6/29 14:41
 **/
public class TwoThreadsCompetition  implements Runnable{
    private volatile int value;

    /**
     * 比较并且交换（CAS方法）
     * @param expectedValue 期望值
     * @param newValue 新值
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            value = newValue;
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        new AtomicInteger();
        new CopyOnWriteArrayList<>();
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r,"Thread1");
        Thread t2 = new Thread(r,"Thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }
}
