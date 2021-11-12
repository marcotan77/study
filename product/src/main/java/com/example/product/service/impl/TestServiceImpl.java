package com.example.product.service.impl;

import com.tan.dubbo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/27 16:25
 **/
@Service
public class TestServiceImpl implements DemoService {
    @Override
    public String test(String name) {
        return "Hello"+name;
    }
}
