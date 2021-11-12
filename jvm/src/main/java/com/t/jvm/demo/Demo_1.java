package com.t.jvm.demo;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/6/18 14:49
 **/
public class Demo_1 {
    /**
     * -XX:NewSize=5242880
     * -XX:MaxNewSize=5242880
     * -XX:InitialHeapSize=10485760
     * -XX:MaxHeapSize=10485760
     * -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=10485760
     * -XX:+UseParNewGC
     * -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:gc.log
     */
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;
        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
