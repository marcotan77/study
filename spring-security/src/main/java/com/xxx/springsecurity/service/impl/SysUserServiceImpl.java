package com.xxx.springsecurity.service.impl;

import com.xxx.springsecurity.mapper.SysUserMapper;
import com.xxx.springsecurity.model.SysUserDO;
import com.xxx.springsecurity.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author com.xxx
 * @since 2023-06-20
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO>implements ISysUserService {


}
