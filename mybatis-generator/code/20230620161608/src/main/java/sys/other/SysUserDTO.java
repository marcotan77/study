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
@ApiModel(value = "SysUserDTO对象", description = "系统用户")
public class SysUserDODTO extends Model<SysUserDO> {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long userId;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String realName;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 密码，加密方式为BCrypt
     */
    @ApiModelProperty("密码，加密方式为BCrypt")
    private String password;

    /**
     * 头像，存的为文件id
     */
    @ApiModelProperty("头像，存的为文件id")
    private Long avatar;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private LocalDate birthday;

    /**
     * 性别：M-男，F-女
     */
    @ApiModelProperty("性别：M-男，F-女")
    private String sex;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty("手机")
    private String phone;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String tel;

    /**
     * 是否是超级管理员：Y-是，N-否
     */
    @ApiModelProperty("是否是超级管理员：Y-是，N-否")
    private String superAdminFlag;

    /**
     * 状态：1-正常，2-冻结
     */
    @ApiModelProperty("状态：1-正常，2-冻结")
    private Integer statusFlag;

    /**
     * 登录次数
     */
    @ApiModelProperty("登录次数")
    private Integer loginCount;

    /**
     * 最后登陆IP
     */
    @ApiModelProperty("最后登陆IP")
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    @ApiModelProperty("最后登陆时间")
    private LocalDateTime lastLoginTime;

    /**
     * 删除标记：Y-已删除，N-未删除
     */
    @ApiModelProperty("删除标记：Y-已删除，N-未删除")
    private String delFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Long createUser;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private Long updateUser;

    private String modifiedBy;

    private LocalDateTime modifiedTime;


}

