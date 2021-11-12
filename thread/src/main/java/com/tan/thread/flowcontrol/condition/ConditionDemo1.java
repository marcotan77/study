package com.tan.thread.flowcontrol.condition;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tan.cs
 * @version 1.0
 * @description Condition基本用法
 * @date 2021/7/2 15:42
 **/
public class ConditionDemo1 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1(){
        lock.lock();
        try {
            System.out.println("条件不满足，开始await");
            condition.await();
            System.out.println("条件满足了，开始执行后续的任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2(){
        lock.lock();
        try {
            System.out.println("准备完成，唤醒其他的线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000);
                conditionDemo1.method2();
            }
        }).start();
        conditionDemo1.method1();
    }

}
