package com.xxx.bizlog.reflect;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字段值对象
 *
 * @author dngzs
 * @date 2023年05月29日14:08:14
 */
@Getter
@AllArgsConstructor
public class FieldValue {

    private final FieldMetadata fieldMetadata;

    private Object value;
}
