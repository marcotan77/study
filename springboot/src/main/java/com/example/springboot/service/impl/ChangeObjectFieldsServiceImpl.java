package com.example.springboot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.springboot.domain.ChangeObjectFields;
import com.example.springboot.mapper.ChangeObjectFieldsMapper;
import com.example.springboot.service.ChangeObjectFieldsService;
@Service
public class ChangeObjectFieldsServiceImpl implements ChangeObjectFieldsService{

    @Resource
    private ChangeObjectFieldsMapper changeObjectFieldsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return changeObjectFieldsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ChangeObjectFields record) {
        return changeObjectFieldsMapper.insert(record);
    }

    @Override
    public int insertSelective(ChangeObjectFields record) {
        return changeObjectFieldsMapper.insertSelective(record);
    }

    @Override
    public ChangeObjectFields selectByPrimaryKey(Integer id) {
        return changeObjectFieldsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ChangeObjectFields record) {
        return changeObjectFieldsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ChangeObjectFields record) {
        return changeObjectFieldsMapper.updateByPrimaryKey(record);
    }

}
