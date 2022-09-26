package com.example.springboot.controller;

import com.example.springboot.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/26 13:06
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SysUserService userService;

//    @GetMapping("/getList")
//    public void list(){
//        userService
//    }


}
