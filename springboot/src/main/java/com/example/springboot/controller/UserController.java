package com.example.springboot.controller;

import com.example.springboot.domain.SysUser;
import com.example.springboot.reponse.Result;
import com.example.springboot.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tanchusheng
 * @version 1.0
 * @description:
 * @date: 2022/9/26 13:25
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private SysUserService userService;

    @GetMapping("/getUserList")
    public Result list() {
        return Result.success("成功11");
    }

    @GetMapping("/getUserById")
    public Result<SysUser> list(Long id) {
        SysUser sysUser = userService.selectByPrimaryKey(id);
        return Result.data(sysUser);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody SysUser sysUser){
        userService.updateByPrimaryKeySelective(sysUser);
        return Result.success("成功");
    }
}
