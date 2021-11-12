package com.tan.thread.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 1000个线程打印日期
 * @date 2021/5/24 15:55
 **/
public class ThreadLocalNormalUsage02 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage02().date(finalI);
                    System.out.println(date);
                }
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds){
        Date date = new Date(1000*seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

}
