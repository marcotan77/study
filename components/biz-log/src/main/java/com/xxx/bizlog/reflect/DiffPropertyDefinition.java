package com.xxx.bizlog.reflect;

import lombok.Data;

/**
 * 值不同字段定义
 *
 * @author dngzs
 * @date 2023年05月29日11:31:46
 */
@Data
public class DiffPropertyDefinition {


    private FieldMetadata fieldMetadata;

    /**
     * 老值
     */
    private Object oldValue;

    /**
     * 新值
     */
    private Object newValue;


    public DiffPropertyDefinition(FieldMetadata fieldMetadata, Object oldValue, Object newValue) {
        this.fieldMetadata = fieldMetadata;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}
