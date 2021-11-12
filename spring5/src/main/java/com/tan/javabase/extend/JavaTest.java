package com.tan.javabase.extend;

import java.text.Collator;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/11 16:39
 **/
public class JavaTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

//    public static void main(String[] args) {
//
//
////        List<String> gisDistricts = new ArrayList<>();
////        gisDistricts.sort(Collator.getInstance(Locale.CHINA));
////        System.out.println(tableSizeFor(17));
////        new HashMap<>(1);
////        Executors.newFixedThreadPool(1);
//
////        for (int i = 0; i < 5; i++) {
////            if (i == 3){
////                return;
////            }
////            System.out.println(i);
////        }
////
//        int a = 1;
//        int b = 1;
//        System.out.println(a ^ b);
//    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 3);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        CountDownLatch latch = new CountDownLatch(3);
        latch.countDown();

//        List<Object> arrayList = new ArrayList<>();
//        arrayList.add("");

//        new Integer(2);
//        new HashMap<>(1);
//        new StringBuffer("");
//        new CountDownLatch(3);
//        a[2]=99;
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + " ");
//        }
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        condition.await();
//        condition.signal();
    }



    /**
     * cap = 5
     *
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1; // 向右移一位  0000 0011
                               //;  0000
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;

    }


}
