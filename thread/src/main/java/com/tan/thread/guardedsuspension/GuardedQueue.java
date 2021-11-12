package com.tan.thread.guardedsuspension;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Tan.cs
 * @version 1.0
 * @description Guarded Suspension模式的“等待-通知”机制是一种非常普遍的线程间协作的方式
 * @date 2021/6/30 15:32
 **/
public class GuardedQueue {
    private final Queue<Integer> sourceList;

    private int count = 0;

    public GuardedQueue() {
        this.sourceList = new LinkedBlockingQueue<>();
    }

    public synchronized Integer get() {
        while (sourceList.isEmpty()) {
            try {
                count++;
                System.out.println(count);
                wait();    // <--- 如果队列为null，等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sourceList.peek();
    }

    public synchronized void put(Integer e) {
        sourceList.add(e);
        notifyAll();  //<--- 通知，继续执行    }
    }
}
