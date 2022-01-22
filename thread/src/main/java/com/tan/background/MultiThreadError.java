package com.tan.background;

import java.text.DecimalFormat;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 运行结果出错
 *
 * @date 2021/8/10 12:26
 **/
public class MultiThreadError {

    public static void main(String[] args) {
        //
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(100));

        DecimalFormat format = new DecimalFormat("0.00");
        System.out.println(format.format((float)1/10));

    }


    
}
