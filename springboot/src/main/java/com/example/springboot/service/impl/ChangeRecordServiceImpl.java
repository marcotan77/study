package com.example.springboot.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.springboot.domain.ChangeRecord;
import com.example.springboot.mapper.ChangeRecordMapper;
import com.example.springboot.service.ChangeRecordService;
@Service
public class ChangeRecordServiceImpl implements ChangeRecordService{

    @Resource
    private ChangeRecordMapper changeRecordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return changeRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ChangeRecord record) {
        return changeRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(ChangeRecord record) {
        return changeRecordMapper.insertSelective(record);
    }

    @Override
    public ChangeRecord selectByPrimaryKey(Integer id) {
        return changeRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ChangeRecord record) {
        return changeRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ChangeRecord record) {
        return changeRecordMapper.updateByPrimaryKey(record);
    }

}
