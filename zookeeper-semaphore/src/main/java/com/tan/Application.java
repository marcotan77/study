package com.tan;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author Tan.cs
 * @version 1.0
 * @description zookeeper 分布式锁-信号量
 * @date 2021/10/15 9:21
 **/
public class Application {
    public static void main(String[] args) throws Exception {
        //设置获取锁失败时，最多可重试3次，每次重试的时间间隔为1000ms
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //Zookeeper集群：这⾥配置3个节点的Zookeeper集群的ip和port
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.15.10.213:5181,10.15.10.213:5182,10.15.10.213:5813", retryPolicy);
        //启动Curator客户端 client.start();
        testSemaphore(client);
    }

    public static void testSemaphore(CuratorFramework client) throws Exception, InterruptedException {
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(client, "/semaphores", 3);
        //获取信号量
        Lease lease = semaphore.acquire();
        // 释放信号量
        semaphore.returnLease(lease);
    }
}
