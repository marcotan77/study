package com.xxx.bizlog.reflect;

import com.google.common.base.Objects;
import com.xxx.bizlog.annotation.LogTag;
import com.xxx.bizlog.converters.Converter;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * 字段定义定义描述
 *
 * @author dngzs
 * @date 2020年08月25日10:31:30
 */
@Data
public class FieldMetadata {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段描述
     */
    private String desc;

    /**
     * 字段描述
     */
    private boolean primaryKey;

    /**
     * 字段
     */
    private Field field;

    /**
     * 转换器
     */
    private Class<? extends Converter> convert;

    /**
     * 所属clazz
     */
    private Class<?> clazz;

    public FieldMetadata(Class clazz, Field field) {
        this.field = field;
        this.clazz = clazz;
        LogTag logTag = field.getAnnotation(LogTag.class);
        this.name = field.getName();
        this.convert = logTag.convert();
        this.desc = logTag.name();
        this.primaryKey = logTag.primaryKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldMetadata that = (FieldMetadata) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
