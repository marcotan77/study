package com.example.springboot.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ChangeObjectInfo implements Serializable {
    /**
    * 主键
    */
    private Integer id;

    /**
    * 变更的对象
    */
    private String objectName;

    /**
    * 变更对象
    */
    private String objectValue;

    /**
    * 变更对象的唯一属性
    */
    private String keyField;

    /**
    * 所属模块
    */
    private String module;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 修改人
    */
    private String modifiedBy;

    /**
    * 修改时间
    */
    private Date modifiedTime;

    /**
    * 数据是否有效（1:有效 0无效）
    */
    private Boolean recFlag;

    private static final long serialVersionUID = 1L;
}