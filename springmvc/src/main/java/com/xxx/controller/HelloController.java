package com.xxx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tanchusheng
 * @date 2023/7/5
 */
@Api("Hello")
@Controller
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation("sayHello")
    @GetMapping("/say")
    public String sayHello(){
        return "Hello World Mvc";
    }



}
