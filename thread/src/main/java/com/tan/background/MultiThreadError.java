package com.tan.background;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 60, TimeUnit.NANOSECONDS, new ArrayBlockingQueue<>(100));

    }
}
