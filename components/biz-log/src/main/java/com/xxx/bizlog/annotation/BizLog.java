package com.xxx.bizlog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BizLog {

    /**
     * 主键值
     *
     * @return
     */
    String value();

    /**
     * 需要处理的PO类型
     *
     * @return
     */
    Class<?> poClazz();


    /**
     * 模块
     *
     * @return
     */
    String module();


    /**
     * 操作序列号
     *
     * @return
     */
    String optSequenceNumber() default "";
}
