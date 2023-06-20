package com.xxx.bizlog.handler;



import com.xxx.bizlog.jdbc.QueryWrapper;
import com.xxx.bizlog.reflect.DiffPropertyDefinition;

import java.util.List;

public interface LogDiffHandler {

    /**
     * 日志处理
     *
     * @param diffPropertyList
     * @param queryWrapper
     */
    void handler(QueryWrapper queryWrapper, List<DiffPropertyDefinition> diffPropertyList);
}
