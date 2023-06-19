package com.dubbo.provider.serivce.impl;

import com.dubbo.api.ITestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestServiceImpl implements ITestService {

    @Override
    public String study(String project) {
        return "数学";
    }


}
