package com.tan.zookeeper.config;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生成 zk 客户端
 *
 * @author tanchusheng
 * @date 2023/8/30 15:02
 */
@Configuration
public class ZookeeperClient {

    @Bean
    private static ZkClient zkClient(ZkClientProperties zookeeperProperties) {
        // 这边如果需要扩展参数的话，可以看 ZkClient 的构造函数
        return new ZkClient(new ZkConnection(zookeeperProperties.getAddress()), zookeeperProperties.getConnectionTimeoutMs());
    }

    /**
     * initMethod = "start"
     * curatorFramework 创建对象之后，调用 curatorFramework 实例的 start 方法
     */
//    @Bean(initMethod = "start")
//    public CuratorFramework curatorFramework(ZkClientProperties zookeeperProperties) {
//        return CuratorFrameworkFactory.newClient(
//                zookeeperProperties.getAddress(),
//                zookeeperProperties.getSessionTimeoutMs(),
//                zookeeperProperties.getConnectionTimeoutMs(),
//                new RetryNTimes(zookeeperProperties.getRetryCount(), zookeeperProperties.getInitElapsedTimeMs())
//        );
//    }
    @Bean(initMethod = "start")
    private static CuratorFramework getZkClient(ZkClientProperties zookeeperProperties) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, zookeeperProperties.getRetryCount(), 5000);
        return CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getAddress())
                .sessionTimeoutMs(zookeeperProperties.getSessionTimeoutMs())
                .connectionTimeoutMs(zookeeperProperties.getConnectionTimeoutMs())
                .retryPolicy(retryPolicy)
                .build();
    }


}
