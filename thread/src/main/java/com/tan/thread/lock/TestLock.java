package com.tan.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tan
 * @date 2021/11/10 17:13
 **/
public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 3; i++) {

            lock.lock();
            Thread.sleep((long) (Math.random()*1000) * 2);
            System.out.println(i);
            lock.unlock();
        }

    }

}
