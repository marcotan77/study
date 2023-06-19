package com.dubbo.consumer.controller;

import com.dubbo.api.ITestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @DubboReference
    private ITestService testService;

    @GetMapping("/study")
    public String test(String str) {
        return testService.study(str);
    }


}
