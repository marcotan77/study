package com.t.jvm.demo;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 老年代触发old gc
 * @date 2021/6/18 15:13
 **/
public class Demo_4 {
    /**
     * -XX:NewSize=104857600
     * -XX:MaxNewSize=104857600
     * -XX:InitialHeapSize=209715200
     * -XX:MaxHeapSize=209715200
     * -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=15
     * -XX:PretenureSizeThreshold=3145728
     * -XX:+UseParNewGC
     * -XX:+UseConcMarkSweepGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:gc.log
     */
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        while (true){
            loadData();
        }

    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000);
    }
}
