package com.example.springboot.service;

import com.example.springboot.domain.ChangeObjectInfo;
public interface ChangeObjectInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(ChangeObjectInfo record);

    int insertSelective(ChangeObjectInfo record);

    ChangeObjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeObjectInfo record);

    int updateByPrimaryKey(ChangeObjectInfo record);

    ChangeObjectInfo getByObjectValue(String value);
}
