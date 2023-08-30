package com.tan.zookeeper.config;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
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

}
