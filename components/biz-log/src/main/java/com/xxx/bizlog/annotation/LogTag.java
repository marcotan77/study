package com.xxx.bizlog.annotation;



import com.xxx.bizlog.converters.Converter;
import com.xxx.bizlog.converters.DefaultConverter;

import java.lang.annotation.*;

/**
 * 业务日志
 *
 * @author dngzs
 * @date 2023年05月25日16:52:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface LogTag {

    /**
     * 字段描述
     * @return
     */
    String name();

    /**
     * 是否主键
     * @return
     */
    boolean primaryKey() default false;

    /**
     * 类型转换器
     * @return
     */
    Class<? extends Converter> convert() default DefaultConverter.class;
}
