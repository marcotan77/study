package com.dubbo.provider.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.api.ITestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestServiceImpl implements ITestService {

    @Override
    public String study(String project) {
        return "数学";
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "  \"shipperId\": 1\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(JSON.toJSONString(jsonObject));
    }


}
