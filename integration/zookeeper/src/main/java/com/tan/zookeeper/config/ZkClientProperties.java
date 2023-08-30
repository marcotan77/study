package com.tan.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 连接 zookeeper 配置类
 *
 * @author tanchusheng
 * @date 2023/8/30 14:58
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZkClientProperties {
    /**
     * 重试次数
     */
    private int retryCount;

    /**
     * 初始重试间隔时间
     */
    private int initElapsedTimeMs;

    /**
     * 最大重试间隔时间
     */
    private int maxElapsedTimeMs;

    /**
     * 连接地址
     */
    private String address;

    /**
     * Session过期时间
     */
    private int sessionTimeoutMs;

    /**
     * 连接超时时间
     */
    private int connectionTimeoutMs;
}
