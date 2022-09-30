package com.example.springboot.service;

import com.example.springboot.domain.ChangeRecord;
public interface ChangeRecordService{


    int deleteByPrimaryKey(Integer id);

    int insert(ChangeRecord record);

    int insertSelective(ChangeRecord record);

    ChangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeRecord record);

    int updateByPrimaryKey(ChangeRecord record);

}
