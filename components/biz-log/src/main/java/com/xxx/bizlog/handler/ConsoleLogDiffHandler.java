package com.xxx.bizlog.handler;

import com.alibaba.fastjson.JSON;

import com.xxx.bizlog.jdbc.QueryWrapper;
import com.xxx.bizlog.reflect.DiffPropertyDefinition;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ConsoleLogDiffHandler implements LogDiffHandler {
    @Override
    public void handler(QueryWrapper queryWrapper, List<DiffPropertyDefinition> diffPropertyList) {
        log.info("模块名 = {}, 修改内容：{}", queryWrapper.getModule(), JSON.toJSON(diffPropertyList));
    }
}
