package com.example.springboot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.springboot.mapper.ChangeObjectInfoMapper;
import com.example.springboot.domain.ChangeObjectInfo;
import com.example.springboot.service.ChangeObjectInfoService;
@Service
public class ChangeObjectInfoServiceImpl implements ChangeObjectInfoService{

    @Resource
    private ChangeObjectInfoMapper changeObjectInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return changeObjectInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ChangeObjectInfo record) {
        return changeObjectInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ChangeObjectInfo record) {
        return changeObjectInfoMapper.insertSelective(record);
    }

    @Override
    public ChangeObjectInfo selectByPrimaryKey(Integer id) {
        return changeObjectInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ChangeObjectInfo record) {
        return changeObjectInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ChangeObjectInfo record) {
        return changeObjectInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public ChangeObjectInfo getByObjectValue(String value) {
        return changeObjectInfoMapper.getByObjectValue(value);
    }
}
