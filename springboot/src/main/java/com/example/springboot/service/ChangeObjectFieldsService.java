package com.example.springboot.service;

import com.example.springboot.domain.ChangeObjectFields;
public interface ChangeObjectFieldsService{


    int deleteByPrimaryKey(Integer id);

    int insert(ChangeObjectFields record);

    int insertSelective(ChangeObjectFields record);

    ChangeObjectFields selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeObjectFields record);

    int updateByPrimaryKey(ChangeObjectFields record);

}
