package com.example.consume.controller;

import com.tan.dubbo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tan.cs
 * @version 1.0
 * @description
 * @date 2021/8/27 16:16
 **/
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("test")
    public String test(String name){
        return demoService.test(name);
    }

}
