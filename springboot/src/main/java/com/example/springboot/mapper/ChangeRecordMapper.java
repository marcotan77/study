package com.example.springboot.mapper;

import com.example.springboot.domain.ChangeRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChangeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChangeRecord record);

    int insertSelective(ChangeRecord record);

    ChangeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChangeRecord record);

    int updateByPrimaryKey(ChangeRecord record);
}