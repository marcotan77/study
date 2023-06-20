package com.xxx.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.dto.Response;
import com.xxx.springsecurity.model.SysUserDO;
import com.xxx.springsecurity.model.dto.SysUserQuery;
import com.xxx.springsecurity.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author com.xxx
 * @since 2023-06-20
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param query
     * @return Result
     */
//    @GetMapping("/page")
//    public Response<Page<SysUserDO>>getSysUserPage(SysUserQuery query){
//
//        return Response.success(sysUserService.page(query.toPage(), Wrappers.query(sysUser)));
//    }


    /**
     * 列表查询-不分页
     *
     * @param query
     * @return Result
     */
    @GetMapping("/getList")
    public Response<List<SysUserDO>>getSysUserList(SysUserQuery query){
        return Response.success(sysUserService.list());
    }


    /**
     * 根据id查询系统用户
     *
     * @param id id
     * @return Result
     */
    @GetMapping("/getById")
    public Response<SysUserDO> getById(@RequestParam Long id){
        return Response.success(sysUserService.getById(id));
    }

    /**
     * 新增系统用户
     *
     * @param sysUser 系统用户
     * @return Result
     */
    @PostMapping("/save")
    public Response<?> save(@RequestBody SysUserDO sysUser){
        return Response.success(sysUserService.save(sysUser));
    }

    /**
     * 修改系统用户
     *
     * @param sysUser 系统用户
     * @return Result
     */
    @PostMapping("updateById")
    public Response<?> updateById(@RequestBody SysUserDO sysUser){
        return Response.success(sysUserService.updateById(sysUser));
    }

    /**
     * 通过id删除系统用户
     *
     * @param id id
     * @return Result
     */
    @PostMapping("/removeById")
    public Response<?> removeById(@RequestParam Long id){
        return Response.success(sysUserService.removeById(id));
    }

}
