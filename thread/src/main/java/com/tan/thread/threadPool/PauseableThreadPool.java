package com.tan.thread.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 演示每个任务执行之前后放狗子函数
 * @date 2021/5/24 15:27
 **/
public class PauseableThreadPool extends ThreadPoolExecutor {


    private boolean isPauseable = false;
    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}
