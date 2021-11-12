package com.tan.thread.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/5/24 15:55
 **/
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(1007);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds){
        // 参数单位事毫秒
        Date date = new Date(1000*seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

}
