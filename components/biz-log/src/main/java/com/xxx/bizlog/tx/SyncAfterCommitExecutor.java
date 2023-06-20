package com.xxx.bizlog.tx;

/**
 * @author dngzs
 * @date 2020-09-24 11:22
 */
public interface SyncAfterCommitExecutor {

    void execute(Runnable runnable);
}
