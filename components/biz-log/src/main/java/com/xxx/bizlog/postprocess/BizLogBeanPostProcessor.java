package com.xxx.bizlog.postprocess;

import com.xxx.bizlog.annotation.BizLog;
import com.xxx.bizlog.registry.LogDefinitionHolder;
import com.xxx.bizlog.utils.AopTargetUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 业务日志处理器
 *
 * @author dngzs
 * @date 2023年05月29日09:14:18
 */
@Component
public class BizLogBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object target = AopTargetUtil.getTarget(bean);
        ReflectionUtils.doWithMethods(target.getClass(), method -> {
            BizLog bizLog = AnnotationUtils.findAnnotation(method, BizLog.class);
            if (bizLog != null) {
                LogDefinitionHolder.forClass(bizLog.poClazz());
                Field primaryKey = LogDefinitionHolder.getPrimaryKey(bizLog.poClazz());
                if (primaryKey == null) {
                    throw new IllegalArgumentException("日志配置错误，未检测到主键列: " + bizLog.poClazz().getName());
                }
                String table = LogDefinitionHolder.getTable(bizLog.poClazz());
                if (StringUtils.isBlank(table)) {
                    throw new IllegalArgumentException("日志配置错误，未检测到表名: " + bizLog.poClazz().getName());
                }
            }
        });
        return bean;
    }
}
