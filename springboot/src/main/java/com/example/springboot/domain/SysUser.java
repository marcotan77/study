package com.example.springboot.domain;

import java.util.Date;
import lombok.Data;

/**
    * 用户信息表
    */
@Data
public class SysUser {
    /**
    * 用户ID
    */
    private Long id;

    /**
    * 用户身份证ID
    */
    private String idCard;

    /**
    * 用户账号
    */
    private String userAcct;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 拼音
    */
    private String userPinyin;

    /**
    * 手机号码
    */
    private String userMobnum;

    /**
    * 短信通知号码
    */
    private String phoneSms;

    /**
    * 邮箱
    */
    private String userEmail;

    /**
    * 密码
    */
    private String loginPw;

    /**
    * 密码盐
    */
    private String pwSalt;

    /**
    * 用户年龄
    */
    private Integer userAge;

    /**
    * 用户性别
    */
    private String userGen;

    /**
    * 用户状态(用户状态(0：离职；1：在职；2：锁定)
    */
    private String userSts;

    /**
    * 用户地址
    */
    private String userAddr;

    /**
    * 用户座机
    */
    private String userTelnum;

    /**
    * 记录标志(0删除;1有效;)
    */
    private String recFlag;

    /**
    * 所属部门
    */
    private Long theDept;

    /**
    * 创建用户ID
    */
    private Long createdBy;

    /**
    * 创建时间
    */
    private Date createdDate;

    /**
    * 修改用户ID
    */
    private Long modifiedBy;

    /**
    * 修改时间
    */
    private Date modifiedDate;

    /**
    * 人员信息ID
    */
    private Long personalId;

    /**
    * 数据同步时间
    */
    private Date loadTime;

    /**
    * 用户类型(用户类型，0：系统用户，1：发货客户，2：承运商客户 , 3：司机用户)
    */
    private String userType;

    private String remark;

    private String oldUserAcct;

    /**
    * 用户头像
    */
    private String profilePicture;
}