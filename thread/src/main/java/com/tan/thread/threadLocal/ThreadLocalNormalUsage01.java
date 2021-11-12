package com.tan.thread.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 10个线程打印日期
 * @date 2021/5/24 15:55
 **/
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
        }
    }

    public String date(int seconds){
        Date date = new Date(1000*seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

}
