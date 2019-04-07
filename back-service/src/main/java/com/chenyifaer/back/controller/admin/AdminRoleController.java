package com.chenyifaer.back.controller.admin;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyifaer.back.entity.dto.AdminRoleDTO;
import com.chenyifaer.back.entity.dto.AdminRolePermissionDTO;
import com.chenyifaer.back.entity.po.AdminRolePO;
import com.chenyifaer.back.entity.po.AdminRolePermissionPO;
import com.chenyifaer.back.entity.vo.AdminRoleVO;
import com.chenyifaer.back.service.AdminRolePermissionService;
import com.chenyifaer.back.service.AdminRoleService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emun.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理 - 后台角色表 前端控制器
 * @author wudh
 * @since 2019-04-06
 */
@Slf4j
@RestController
@RequestMapping("/admin/role")
@Api(value = "账号管理",tags = {"账号管理 - 后台角色管理"})
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRolePermissionService adminRolePermissionService;

    @ApiOperation(value = "查询角色列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", dataType = "string"),
        @ApiImplicitParam(name = "status", value = "状态 0：禁用 1：启用", dataType = "int"),
    })
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("function start AdminRoleController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("function AdminRoleController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(adminRoleDTO.getPageIndex(),adminRoleDTO.getPageSize());
        List<AdminRoleVO> list = this.adminRoleService.getList(adminRoleDTO);
        PageInfo<AdminRoleVO> pageList = new PageInfo<>(list);
        log.debug("function end AdminRoleController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,pageList);
    }

    @ApiOperation(value = "禁用/启用角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态 0：禁用 1：启用", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/disableRole" , method = RequestMethod.POST)
    public JsonResult disableRole(@RequestBody @Validated(AdminRoleDTO.Disable.class) AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("function start AdminRoleController - disableRole");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("function AdminRoleController - disableRole 参数校验失败");
            return check;
        }
        AdminRolePO adminRolePO = new AdminRolePO()
                .setAdminRoleId(adminRoleDTO.getAdminRoleId())
                .setStatus(adminRoleDTO.getStatus())
                .setUpdateTime(DateUtil.getTime());
        boolean flag = this.adminRoleService.updateById(adminRolePO);

        //若flag为true
        if(flag){
            log.debug("function end AdminRoleController - disableRole , 禁用/启用角色成功 , 禁用的角色信息为：" + adminRolePO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("function AdminRoleController - disableRole , 禁用/启用角色成功");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "更新角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", dataType = "string"),
        @ApiImplicitParam(name = "adminRoleText", value = "角色介绍", dataType = "int"),
    })
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(AdminRoleDTO.Update.class) AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("function start AdminRoleController - update");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("function AdminRoleController - update 参数校验失败");
            return check;
        }

        AdminRolePO adminRolePO = new AdminRolePO()
                .setAdminRoleId(adminRoleDTO.getAdminRoleId())
                .setAdminRoleName(adminRoleDTO.getAdminRoleName())
                .setAdminRoleText(adminRoleDTO.getAdminRoleText())
                .setUpdateTime(DateUtil.getTime());
        boolean flag = this.adminRoleService.updateById(adminRolePO);

        //若flag为true
        if(flag){
            log.debug("function end AdminRoleController - update , 更新角色成功，更新的角色信息为：" + adminRolePO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("function AdminRoleController - update 更新角色失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "角色授权")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", dataType = "string"),
        @ApiImplicitParam(name = "adminRoleText", value = "角色介绍", dataType = "int"),
    })
    @RequestMapping(value = "/permission" , method = RequestMethod.POST)
    public JsonResult permission(@RequestBody @Validated AdminRolePermissionDTO adminRolePermissionDTO , BindingResult br){
        log.debug("function start AdminRoleController - permission");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("function AdminRoleController - permission 参数校验失败");
            return check;
        }

        //清空对应角色的权限
        boolean deleteFlag = this.adminRolePermissionService.remove(
            new UpdateWrapper(new AdminRolePermissionPO().setAdminRoleId(adminRolePermissionDTO.getAdminRoleId())));
        //若清空操作成功，则进行插入
        if(deleteFlag){
            adminRolePermissionDTO.getAdminMenuList().forEach(x -> {
                x.getAdminPermissionIdList().forEach(j -> {
                    AdminRolePermissionPO adminRolePermissionPO = new AdminRolePermissionPO()
                            .setAdminRoleId(adminRolePermissionDTO.getAdminRoleId())
                            .setAdminPermissionId(j)
                            .setAdminMenuId(x.getAdminMenuId());
                    //插入权限表
                    this.adminRolePermissionService.save(adminRolePermissionPO);
                });
            });
        }
        log.debug("function end AdminRoleController - permission , 角色授权成功，授权的角色信息为：" + adminRolePermissionDTO);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
    }

}
