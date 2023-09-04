package com.tan.zookeeper.controller;

import com.tan.zookeeper.service.InterprocessMutexLock;
import com.tan.zookeeper.service.ZkWatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanchusheng
 * @date 2023/8/30 17:59
 */
@RestController
public class TestController {

    private final InterprocessMutexLock interprocessMutexLock;
    private final ZkWatcher zkWatcher;


    public TestController(
            InterprocessMutexLock interprocessMutexLock,
            ZkWatcher zkWatcher
    ) {
        this.interprocessMutexLock = interprocessMutexLock;
        this.zkWatcher = zkWatcher;
    }

    /**
     * 测试 zkClient 的 SubscribeDataChanges 方法
     */
    @GetMapping("/zk-client/subscribe/data")
    public void testZkClientSubscribeDataChanges() throws InterruptedException {
        zkWatcher.testSubscribeDataChanges();
    }

    /**
     * 测试 zkClient 的 SubscribeChildChanges 方法
     */
    @GetMapping("/zk-client/subscribe/child")
    public void testZkClientSubscribeChildChanges() throws InterruptedException {
        zkWatcher.testSubscribeChildChanges();
    }

    /**
     * 测试 curator 提供的 分布式可重入排它锁
     */
    @GetMapping("/lock/mutex")
    public void testMutexLock () {
        interprocessMutexLock.test("/lock/mutex");
    }


    /**
     * 测试 curator 提供的 分布式可重入排它锁
     */
    @GetMapping("/testTransaction")
    public void testTransaction () {
        try {
            interprocessMutexLock.testTransaction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
