package com.xxx.bizlog.jdbc;

import com.google.common.base.CaseFormat;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * 查询封装
 *
 * @author dngzs
 * @date 2023年05月30日14:04:57
 */
@Data
public class PrimaryParam {
    /**
     * 字段名
     */
    private String column;

    /**
     * 字段
     */
    private Field field;

    /**
     * 字段值
     */
    private Object value;

    public PrimaryParam(Field field, Object value) {
        this.field = field;
        this.value = value;
        column = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
    }
}
