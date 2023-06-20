package com.xxx.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.springsecurity.model.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author com.xxx
 * @since 2023-06-20
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {

}
