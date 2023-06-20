package com.xxx.bizlog.config;


import com.xxx.bizlog.aspect.LogAspect;
import com.xxx.bizlog.handler.ConsoleLogDiffHandler;
import com.xxx.bizlog.jdbc.JdbcUtils;
import com.xxx.bizlog.postprocess.BizLogBeanPostProcessor;
import com.xxx.bizlog.tx.AsyncAfterCommitExecutorImpl;
import com.xxx.bizlog.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class BizLogAutoConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "asyncAfterCommitExecutor")
    public AsyncAfterCommitExecutorImpl syncAfterCommit() {
        return new AsyncAfterCommitExecutorImpl();
    }

    @Bean
    public JdbcUtils jdbcUtils() {
        return new JdbcUtils();
    }

    @Bean
    public SpringUtils customSpringUtils() {
        return new SpringUtils();
    }

    @Bean
    public ConsoleLogDiffHandler consoleLogDiffHandler() {
        return new ConsoleLogDiffHandler();
    }


    @Bean
    public BizLogBeanPostProcessor bizLogBeanPostProcessor() {
        return new BizLogBeanPostProcessor();
    }
}
