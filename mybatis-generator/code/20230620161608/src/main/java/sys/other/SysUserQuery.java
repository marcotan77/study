package .sys.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import BasePageQuery;
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
@ApiModel(value = "SysUserQuery对象", description = "系统用户")
public class SysUserQuery extends BasePageQuery {

    @ApiModelProperty("主键")
    private Long userId;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码，加密方式为BCrypt")
    private String password;

    @ApiModelProperty("头像，存的为文件id")
    private Long avatar;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("性别：M-男，F-女")
    private String sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("是否是超级管理员：Y-是，N-否")
    private String superAdminFlag;

    @ApiModelProperty("状态：1-正常，2-冻结")
    private Integer statusFlag;

    @ApiModelProperty("登录次数")
    private Integer loginCount;

    @ApiModelProperty("最后登陆IP")
    private String lastLoginIp;

    @ApiModelProperty("最后登陆时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("删除标记：Y-已删除，N-未删除")
    private String delFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人")
    private Long updateUser;

    private String modifiedBy;

    private LocalDateTime modifiedTime;


}

