package com.example.springboot.mapper;

import com.example.springboot.domain.ChangeObjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChangeObjectInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeObjectInfo record);

    int insertSelective(ChangeObjectInfo record);

    ChangeObjectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeObjectInfo record);

    int updateByPrimaryKey(ChangeObjectInfo record);

    ChangeObjectInfo getByObjectValue(@Param("value") String value);
}