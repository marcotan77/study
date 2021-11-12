package com.tan.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/3 14:28
 **/
public class AQSDemo extends AbstractQueuedSynchronizer {
    protected AQSDemo() {
        super();
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
//        new CountDownLatch();
//        new Semaphore()
    }
}