package com.xxx.springsecurity.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户 query
 * </p>
 *
 * @author com.xxx
 * @since 2023-06-20
 */
@Getter
@Setter
@Accessors(chain = true)
public class SysUserQuery {

    private Long userId;

    private String realName;

    private String nickName;

    private String account;

    private String password;

    private Long avatar;

    private LocalDate birthday;

    private String sex;

    private String email;

    private String phone;

    private String tel;

    private String superAdminFlag;

    private Integer statusFlag;

    private Integer loginCount;

    private String lastLoginIp;

    private LocalDateTime lastLoginTime;

    private String delFlag;

    private LocalDateTime createTime;

    private Long createUser;

    private LocalDateTime updateTime;

    private Long updateUser;

    private String modifiedBy;

    private LocalDateTime modifiedTime;


}

