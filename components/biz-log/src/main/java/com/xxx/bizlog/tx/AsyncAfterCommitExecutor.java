package com.xxx.bizlog.tx;

/**
 * @author dngzs
 * @date 2023年05月26日16:36:13
 */
@FunctionalInterface
public interface AsyncAfterCommitExecutor {

    void execute(Runnable runnable);

}
