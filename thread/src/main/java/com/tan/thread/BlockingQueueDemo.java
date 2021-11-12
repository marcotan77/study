package com.tan.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/7/9 17:06
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            blockingQueue.add(i);
        }
        System.out.println(blockingQueue);
//        boolean addFlag = blockingQueue.add(7);
        boolean offer = blockingQueue.offer(7);
        System.out.println(offer);

    }
}
