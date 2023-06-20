package com.xxx.bizlog.tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.concurrent.Executor;

/**
 * @author dngzs
 * @create 2023年05月26日16:36:40
 */
@Slf4j
public abstract class RunnableTxCommit extends AbstractTxAfterCommit<Runnable> implements Executor {

    @Override
    public void execute(Runnable runnable) {
        log.info("Submitting new runnable {} to run after commit", runnable);
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            log.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", runnable);
            notActiveTransactionExecute(runnable);
            return;
        }
        super.execute(runnable);
    }

    /**
     * 非事务方式运行
     *
     * @param runnable
     */
    public abstract void notActiveTransactionExecute(Runnable runnable);
}
