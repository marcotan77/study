package com.example.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicket {



    public static void main(String[] args) {
        new Thread(()->{
            new testThread().test();
            },"A").start();
    }

    public static class testThread{
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void test(){
            System.out.println(Thread.currentThread().getName());
        }

    }
}


