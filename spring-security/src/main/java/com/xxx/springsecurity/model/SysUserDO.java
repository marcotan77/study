package com.xxx.springsecurity.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author com.xxx
 * @since 2023-06-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class SysUserDO {

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码，加密方式为BCrypt
     */
    @TableField("password")
    private String password;

    /**
     * 头像，存的为文件id
     */
    @TableField("avatar")
    private Long avatar;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 性别：M-男，F-女
     */
    @TableField("sex")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 电话
     */
    @TableField("tel")
    private String tel;

    /**
     * 是否是超级管理员：Y-是，N-否
     */
    @TableField("super_admin_flag")
    private String superAdminFlag;

    /**
     * 状态：1-正常，2-冻结
     */
    @TableField("status_flag")
    private Integer statusFlag;

    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;

    /**
     * 最后登陆IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 删除标记：Y-已删除，N-未删除
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    @TableField("modified_by")
    private String modifiedBy;

    @TableField("modified_time")
    private LocalDateTime modifiedTime;


}
