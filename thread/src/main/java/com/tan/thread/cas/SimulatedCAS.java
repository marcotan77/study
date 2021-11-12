package com.tan.thread.cas;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 模拟CAS操作，等价代码
 * @date 2021/6/29 14:41
 **/
public class SimulatedCAS {
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
}
