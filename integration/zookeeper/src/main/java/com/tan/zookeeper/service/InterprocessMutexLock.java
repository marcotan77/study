package com.tan.zookeeper.service;

import com.xxx.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.api.transaction.TransactionOp;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * zookeeper 分布式锁 是可重入 互斥锁
 *
 * @author tanchusheng
 * @date 2023/8/31 16:20
 */
@Slf4j
@Service
public class InterprocessMutexLock {

    private final CuratorFramework curatorFramework;
    private int inventory = 5;

    public InterprocessMutexLock(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public Response<List<CuratorTransactionResult>> testTransaction() {
        try {

            TransactionOp transactionOp = curatorFramework.transactionOp();
            CuratorOp curatorOp = transactionOp.check().forPath("/test/op1");
            CuratorOp createOp = transactionOp.create().forPath("/test/op1", "createData".getBytes(StandardCharsets.UTF_8));
            CuratorOp createOp1 = transactionOp.create().forPath("/test/op2", "createData1".getBytes(StandardCharsets.UTF_8));
            CuratorOp createOp2 = transactionOp.create().forPath("/test/op3", "createData2".getBytes(StandardCharsets.UTF_8));
            CuratorOp setOp = transactionOp.setData().forPath("/test/op2", "setData".getBytes(StandardCharsets.UTF_8));
            CuratorOp deleteOp = transactionOp.delete().forPath("/test/op3");
            List<CuratorTransactionResult> curatorTransactionResults = curatorFramework.transaction().forOperations(curatorOp, createOp, createOp1, createOp2, setOp, deleteOp);
            curatorTransactionResults.forEach(transactionResult -> {
                log.info("forPath: {}-- ResultPath: {}-- Type: {}", transactionResult.getForPath(), transactionResult.getResultPath(), transactionResult.getType());
            });
            return Response.success(curatorTransactionResults);
        } catch (Exception e) {
            log.error("", e);
        }
        return Response.success();
    }

    public void test(String lockPath) {

        InterProcessMutex lock = new InterProcessMutex(curatorFramework, lockPath);
        //模拟 50 个线程抢锁
        for (int i = 0; i < 10; i++) {
            new Thread(new TestThread(i, lock)).start();
        }

    }

    class TestThread implements Runnable {

        private final Integer threadFlag;
        private final InterProcessMutex lock;

        public TestThread(Integer threadFlag, InterProcessMutex lock) {
            this.threadFlag = threadFlag;
            this.lock = lock;
        }

        @Override
        public void run() {

            try {

                lock.acquire();
                log.info("第 {} 个线程获取到了锁, 库存还剩余：{}", threadFlag, inventory);
                if (inventory > 0) {
                    inventory--;
                    log.info("库存还剩余：{}", inventory);
                } else {
                    log.info("已经没有库存了 inventory: {}, 线程 {} 没有买到", inventory, threadFlag);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放锁
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
