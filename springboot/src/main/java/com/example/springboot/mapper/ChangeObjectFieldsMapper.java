package com.example.springboot.mapper;

import com.example.springboot.domain.ChangeObjectFields;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChangeObjectFieldsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeObjectFields record);

    int insertSelective(ChangeObjectFields record);

    ChangeObjectFields selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeObjectFields record);

    int updateByPrimaryKey(ChangeObjectFields record);
}