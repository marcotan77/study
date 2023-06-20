package com.xxx.bizlog.registry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.collect.Sets;
import com.xxx.bizlog.annotation.LogTag;
import com.xxx.bizlog.annotation.Table;
import com.xxx.bizlog.reflect.FieldMetadata;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 日志字段缓存
 *
 * @author dngzs
 * @date 2023年05月29日10:33:27
 */
public final class LogDefinitionHolder {

    private static final Map<Class<?>, Set<FieldMetadata>> LOG_BEAN_CLASS_FIELD_CACHE = new HashMap<>(64);
    private static final Map<Class<?>, Field> LOG_PRIMARY_KEY_FIELD_CACHE = new HashMap<>(64);
    private static final Map<Class<?>, String> LOG_TABLE_CACHE = new HashMap<>(64);
    private static final Map<Class<?>, Field> UPDATER_FIELD_CACHE = new HashMap<>(64);

    private static final String UPDATER_FIELD_NAME = "modifiedBy";

    private LogDefinitionHolder() {

    }

    /**
     * 获取主键字段
     *
     * @param clazz
     * @return
     */
    public static Field getPrimaryKey(Class<?> clazz) {
        return LOG_PRIMARY_KEY_FIELD_CACHE.get(clazz);
    }


    /**
     * 获取更新人字段
     *
     * @param clazz
     * @return
     */
    public static Field getUpdater(Class<?> clazz) {
        return UPDATER_FIELD_CACHE.get(clazz);
    }


    /**
     * 获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTable(Class<?> clazz) {
        return LOG_TABLE_CACHE.get(clazz);
    }

    /**
     * 为给定的class创建LogRecordDefinition
     *
     * @param beanClass
     * @return
     */
    public static Set<FieldMetadata> forClass(Class<?> beanClass) {
        Set<FieldMetadata> logRecordDefinitions = LOG_BEAN_CLASS_FIELD_CACHE.get(beanClass);
        if (CollectionUtils.isNotEmpty(logRecordDefinitions)) {
            return logRecordDefinitions;
        }
        Field primaryKeyFieldCache = LOG_PRIMARY_KEY_FIELD_CACHE.get(beanClass);
        String tableCache = LOG_TABLE_CACHE.get(beanClass);
        Set<FieldMetadata> initLogRecordDefinitions = Sets.newHashSet();
        synchronized (FieldMetadata.class) {
            logRecordDefinitions = LOG_BEAN_CLASS_FIELD_CACHE.get(beanClass);
            if (CollectionUtils.isNotEmpty(logRecordDefinitions)) {
                return logRecordDefinitions;
            }
            //处理表名
            TableName tableName = beanClass.getAnnotation(TableName.class);
            Table table = beanClass.getAnnotation(Table.class);
            if (tableName != null && StringUtils.isBlank(tableCache)) {
                LOG_TABLE_CACHE.put(beanClass, tableName.value());
            }
            if (table != null && StringUtils.isBlank(tableCache)) {
                LOG_TABLE_CACHE.put(beanClass, table.value());
            }
            ReflectionUtils.doWithFields(beanClass, field -> {
                //更新人列
                if (UPDATER_FIELD_NAME.equalsIgnoreCase(field.getName())) {
                    UPDATER_FIELD_CACHE.put(beanClass, field);
                }
                LogTag logRecord = AnnotationUtils.getAnnotation(field, LogTag.class);
                TableId tableId = AnnotationUtils.getAnnotation(field, TableId.class);
                if (logRecord != null) {
                    initLogRecordDefinitions.add(new FieldMetadata(beanClass, field));
                    //主键列
                    if ((logRecord.primaryKey() || tableId != null) && primaryKeyFieldCache == null) {
                        LOG_PRIMARY_KEY_FIELD_CACHE.put(beanClass, field);
                    }
                } else {
                    if (tableId != null && primaryKeyFieldCache == null) {
                        LOG_PRIMARY_KEY_FIELD_CACHE.put(beanClass, field);
                    }
                }
            });
            LOG_BEAN_CLASS_FIELD_CACHE.put(beanClass, initLogRecordDefinitions);
        }
        return initLogRecordDefinitions;
    }


    /**
     * 更新人
     *
     * @param beanClass
     * @return
     */
    public static Field forClassUpdater(Class<?> beanClass) {
        Field updateField = UPDATER_FIELD_CACHE.get(beanClass);
        if (updateField != null) {
            return updateField;
        }
        synchronized (LogDefinitionHolder.class) {
            ReflectionUtils.doWithFields(beanClass, field -> {
                //更新人列
                if (UPDATER_FIELD_NAME.equalsIgnoreCase(field.getName())) {
                    UPDATER_FIELD_CACHE.put(beanClass, field);
                }
            });
        }
        return UPDATER_FIELD_CACHE.get(beanClass);
    }
}
