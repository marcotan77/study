package com.example.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 序列号生成
 * @author tanchusheng
 * @date 2023/8/2
 */
@Slf4j
@Component
public class SerialNumberGenerator {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存目录结构
     */
    private static final String CTMS_BASAL_NUM="ztozy:ctms:basal:numutil:";

    public static String YY = "yy";
    public static String YY_MM = "yyMM";
    public static String YY_MM_dd = "yyMMdd";


    /**
     * 单号增加年月日生成
     *
     * @param keyName redis存储key值
     * @param lenght 补足多少位
     * @param prefix 单号前缀
     * @return
     */
    public String generateYYMMDDNum(String keyName, int lenght, String prefix) {
        prefix = StringUtils.nvl(prefix,"");
        prefix += DateUtils.dateTimeNow(YY_MM_dd);
        return generateNum(keyName,lenght,prefix);
    }
    /**
     * 单号增加年生成
     *
     * @param keyName redis存储key值
     * @param lenght 补足多少位
     * @param prefix 单号前缀
     * @return
     */
    public String generateYYNum(String keyName, int lenght, String prefix) {
        prefix = StringUtils.nvl(prefix,"");
        prefix += DateUtils.dateTimeNow(YY);
        return generateNum(keyName,lenght,prefix);
    }
    /**
     * 单号补0生成
     *
     * @param keyName redis存储key值
     * @param lenght 补足多少位
     * @param prefix 单号前缀
     * @return
     */
    public String generateNum(String keyName, int lenght, String prefix) {
        Integer serialNumber = getSerialNumber(keyName);
        prefix = StringUtils.nvl(prefix,"");
        return prefix + String.format("%0" + lenght + "d", serialNumber);
    }

    /**
     * 数量自增 已设置过期时间,24小时后重新自增
     *
     * @param keyName
     * @return
     */
    public Integer getSerialNumber(String keyName) {
        keyName = CTMS_BASAL_NUM + keyName;
        try {
            RedisAtomicLong entityIdCounter = new RedisAtomicLong(keyName, redisTemplate.getConnectionFactory());
            long incr = entityIdCounter.getAndIncrement();
            if (incr == 0) {// 初始设置过期时间
                // 设置过期时间 24小时
                entityIdCounter.expire(24, TimeUnit.HOURS);
                // 这里取第二次 incr 就是从1开始了,默认从0开始
                incr = entityIdCounter.getAndIncrement();
            }
            log.debug(keyName + "==========" + incr);
            return (int) incr;
        } catch (Exception e) {
            log.error("======redisIncr======", e);
        }
        return null;
    }
}
