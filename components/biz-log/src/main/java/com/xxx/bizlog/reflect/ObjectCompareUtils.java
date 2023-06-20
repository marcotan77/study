package com.xxx.bizlog.reflect;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xxx.bizlog.converters.Converter;
import com.xxx.bizlog.converters.DefaultConverter;
import com.xxx.bizlog.registry.LogDefinitionHolder;
import com.xxx.bizlog.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 属性对比
 *
 * @author dngzs
 * @date 2023年05月25日16:57:16
 */
@Slf4j
public class ObjectCompareUtils {


    private static final ObjectFactory OBJECT_FACTORY = new DefaultObjectFactory();

    private static final Map<Class<? extends Converter>, Converter> CONVERTER_CACHE_MAP = Maps.newConcurrentMap();

    /**
     * 属性对比
     *
     * @param before
     * @param after
     * @return
     */
    public static List<DiffPropertyDefinition> getDifferentProperty(Object before, Object after) {
        List<DiffPropertyDefinition> diffList = Lists.newArrayList();
        if (before == null || after == null) {
            return diffList;
        }
        Set<FieldMetadata> fieldMetadataSet = LogDefinitionHolder.forClass(before.getClass());
        if (CollectionUtils.isEmpty(fieldMetadataSet)) {
            return diffList;
        }
        //获取前值
        Map<String, FieldValue> beforeMetadataMap = Maps.newHashMap();
        for (FieldMetadata fieldMetadata : fieldMetadataSet) {
            // 设置字段可访问， 否则无法访问private修饰的变量值
            try {
                Field field = fieldMetadata.getField();
                field.setAccessible(true);
                Object value = convert(field.get(before), fieldMetadata.getConvert());
                beforeMetadataMap.put(fieldMetadata.getName(), new FieldValue(fieldMetadata, value));
            } catch (IllegalAccessException e) {
                log.error("反射获取值失败", e);
            }
        }

        //获取后值
        Map<String, FieldValue> afterMetadataMap = Maps.newHashMap();
        for (FieldMetadata fieldMetadata : fieldMetadataSet) {
            // 设置字段可访问， 否则无法访问private修饰的变量值
            try {
                Field field = fieldMetadata.getField();
                field.setAccessible(true);
                Object value = convert(field.get(after), fieldMetadata.getConvert());
                afterMetadataMap.put(fieldMetadata.getName(), new FieldValue(fieldMetadata, value));
            } catch (IllegalAccessException e) {
                log.error("反射获取值失败", e);
            }
        }
        afterMetadataMap.forEach((k, v) -> {
            FieldValue fieldValue = beforeMetadataMap.get(k);
            if (fieldValue == null) {
                return;
            }
            Object oldValue = fieldValue.getValue();
            Object newValue = v.getValue();
            FieldMetadata fieldMetadata = fieldValue.getFieldMetadata();
            if (!Objects.equal(oldValue, newValue)) {
                diffList.add(new DiffPropertyDefinition(fieldMetadata, oldValue, newValue));
            }
        });
        return diffList;
    }


    /**
     * 值处理转换器处理
     *
     * @param value
     * @param converterClazz
     * @return
     */
    private static Object convert(Object value, Class<? extends Converter> converterClazz) {
        if (!converterClazz.isAssignableFrom(DefaultConverter.class)) {
            Converter instanceConverter = CONVERTER_CACHE_MAP.computeIfAbsent(converterClazz, method -> {
                Converter converter = null;
                try {
                    converter = SpringUtils.getBean(converterClazz);
                } catch (NoSuchBeanDefinitionException e) {
                    //ignore
                }
                if (converter == null) {
                    converter = OBJECT_FACTORY.create(converterClazz);
                }
                return converter;
            });
            if (instanceConverter != null) {
                return instanceConverter.convert(value);
            }
        }
        return value;
    }
}
