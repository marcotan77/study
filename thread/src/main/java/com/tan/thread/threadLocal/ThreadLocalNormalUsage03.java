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
public class ThreadLocalNormalUsage03 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws InterruptedException{
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println(date);
                }
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds){
        Date date = new Date(1000*seconds);
        String s;
        synchronized (ThreadLocalNormalUsage03.class){
           s = dateFormat.format(date);
        }
        return s;
    }

}
