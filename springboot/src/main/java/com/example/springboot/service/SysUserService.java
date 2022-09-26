package com.example.springboot.service;

import com.example.springboot.domain.SysUser;
public interface SysUserService{


    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    Object updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);



}
