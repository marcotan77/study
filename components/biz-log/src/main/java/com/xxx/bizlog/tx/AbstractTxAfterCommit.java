package com.xxx.bizlog.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dngzs
 * @date 2023年05月26日16:35:55
 */
@Slf4j
public abstract class AbstractTxAfterCommit<T> extends TransactionSynchronizationAdapter {

    protected final ThreadLocal<List<T>> RUNNABLE_THREAD_LOCAL = new ThreadLocal<>();

    public void execute(T t) {
        List<T> threadRunnable = RUNNABLE_THREAD_LOCAL.get();
        if (threadRunnable == null) {
            threadRunnable = new ArrayList<>();
            RUNNABLE_THREAD_LOCAL.set(threadRunnable);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnable.add(t);
    }

    @Override
    public void afterCompletion(int status) {
        log.info("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        RUNNABLE_THREAD_LOCAL.remove();
    }
}
