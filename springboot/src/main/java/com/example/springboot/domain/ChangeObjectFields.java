package com.example.springboot.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ChangeObjectFields implements Serializable {
    private Integer id;

    /**
    * 字段名称
    */
    private String fieldName;

    /**
    * 字段值
    */
    private String fieldValue;

    /**
    * 所属对象ID
    */
    private Integer objectId;

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
    * 数据状态（1.有效0：无效）
    */
    private Boolean recFlag;

    private static final long serialVersionUID = 1L;
}