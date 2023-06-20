package com.xxx.bizlog.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询参数
 *
 * @author dngzs
 * @date 2023年05月26日14:58:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryWrapper {

    /**
     * 表名
     */
    private String table;

    /**
     * 模块名
     */
    private String module;

    /**
     * 返回类型
     */
    private Class<?> returnClazz;

    /**
     * 主键
     */
    private PrimaryParam primaryParam;

    /**
     * 操作序列号
     */
    private String optSequenceNumber;


    /**
     * 更新人
     */
    private Long modifiedBy;
}
