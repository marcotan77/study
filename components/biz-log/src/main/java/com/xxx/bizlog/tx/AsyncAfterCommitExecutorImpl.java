package com.xxx.bizlog.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务执行前后执行一些列操作
 *
 * @author dngzs
 * @date 2023年05月26日16:36:25
 */
@Component
@Slf4j
public class AsyncAfterCommitExecutorImpl extends RunnableTxCommit implements AsyncAfterCommitExecutor {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(2, 2, 20, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void afterCommit() {
        List<Runnable> threadRunnable = RUNNABLE_THREAD_LOCAL.get();
        log.info("Transaction successfully committed, executing {} threadRunnable", threadRunnable.size());
        for (int i = 0; i < threadRunnable.size(); i++) {
            Runnable runnable = threadRunnable.get(i);
            log.info("Executing runnable {}", runnable);
            try {
                THREAD_POOL_EXECUTOR.execute(runnable);
            } catch (RuntimeException e) {
                log.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    @PreDestroy
    public void destroy() {
        THREAD_POOL_EXECUTOR.shutdown();
    }

    @Override
    public void notActiveTransactionExecute(Runnable runnable) {
        THREAD_POOL_EXECUTOR.execute(runnable);
    }
}
