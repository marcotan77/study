package .sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import .sys.entity.SysUserDO;
import .sys.service.ISysUserService;
import .sys.other.SysUserQuery;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "系统用户")
@RestController
@RequestMapping("/sys/sys-user-do")
public class SysUserController {


    @Autowired
    private ISysUserService ISysUserService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询
     *
     * @param query
     * @return Result
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Response<Page<SysUserDO>>getSysUserPage(SysUserQuery query){
      
        return Response.success(sysUserService.page(query.toPage(), Wrappers.query(sysUser)));
    }


    /**
     * 列表查询-不分页
     *
     * @param query
     * @return Result
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/getList")
    public Response<List<SysUserDO>>getSysUserList(SysUserQuery query){

        QueryWrapper<SysUserDO> ew = Wrappers.query(sysUser);
        ew.last(null != query.getListLimit(), "limit " + query.getListLimit());
        return Response.success(sysUserService.list(ew));
    }


    /**
     * 根据id查询系统用户
     *
     * @param id id
     * @return Result
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
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
    @ApiOperation(value = "新增系统用户", notes = "新增系统用户")
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
    @ApiOperation(value = "修改系统用户", notes = "修改系统用户")
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
    @ApiOperation(value = "通过id删除 系统用户", notes = "通过id删除系统用户")
    @PostMapping("/removeById")
    public Response<?> removeById(@RequestParam Long id){
        return Response.success(sysUserService.removeById(id));
    }

}
