package com.example.springboot.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ChangeRecord implements Serializable {
    private Integer id;

    /**
    * 业务日志类型（1：修改 2：删除）
    */
    private Integer businessLogType;

    /**
    * 所属模块
    */
    private String module;

    /**
    * 对象
    */
    private String objectValue;

    /**
    * 数据编号
    */
    private String dataNo;

    /**
    * 变更的字段
    */
    private String fieldName;

    /**
    * 原来的值
    */
    private String originalValue;

    /**
    * 修改的值
    */
    private String modifiedValue;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    private Date createdTime;

    private static final long serialVersionUID = 1L;
}