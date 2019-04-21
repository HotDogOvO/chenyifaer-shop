package com.chenyifaer.back.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.AdminRoleDTO;
import com.chenyifaer.back.entity.po.AdminRolePO;
import com.chenyifaer.back.entity.po.AdminUserRolePO;
import com.chenyifaer.back.entity.vo.AdminRoleNameVO;
import com.chenyifaer.back.entity.vo.AdminRoleVO;
import com.chenyifaer.back.service.AdminRolePermissionService;
import com.chenyifaer.back.service.AdminRoleService;
import com.chenyifaer.back.service.AdminUserRoleService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
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

import java.util.ArrayList;
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
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private AdminRolePermissionService adminRolePermissionService;

    @ApiOperation(value = "查询角色列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", dataType = "string"),
        @ApiImplicitParam(name = "status", value = "状态 0：禁用 1：启用", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("【START】 - function AdminRoleController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminRoleController - list 参数校验失败");
            return check;
        }
        List<AdminRoleVO> list = this.adminRoleService.getList(adminRoleDTO);
        log.debug("【END】 - function AdminRoleController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "查询角色下拉框")
    @RsaAnnotation
    @RequestMapping(value = "/getRoleName" , method = RequestMethod.POST)
    public JsonResult getRoleName(){
        log.debug("【START】 - function AdminRoleController - getRoleName");
        List<AdminRolePO> list = this.adminRoleService.list();
        List<AdminRoleNameVO> roleList = new ArrayList<AdminRoleNameVO>();
        list.forEach(x ->{
            roleList.add(new AdminRoleNameVO()
                    .setAdminRoleName(x.getAdminRoleName())
                    .setAdminRoleId(x.getAdminRoleId()));
        });
        log.debug("【END】 - function AdminRoleController - getRoleName 返回的结果为：" + roleList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,roleList);
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", required = true, dataType = "string"),
        @ApiImplicitParam(name = "adminRoleText", value = "角色描述", required = true, dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.ADMIN_ROLE_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_ADMIN_ROLE_ADD)
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult addRole(@RequestBody @Validated(AdminRoleDTO.Add.class) AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("【START】 - function AdminRoleController - addRole");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminRoleController - addRole 参数校验失败");
            return check;
        }

        //判断当前角色名是否存在
        int count = this.adminRoleService.count(new QueryWrapper<>(new AdminRolePO()
                .setAdminRoleName(adminRoleDTO.getAdminRoleName())));
        if(count > 0){
            log.error("【ERROR】 - function AdminRoleController - addRole , 角色【{}】已经存在了",adminRoleDTO.getAdminRoleName());
            return ResponseResult.Success(ResultCodeEnums.CHECK_005);
        }

        AdminRolePO adminRolePO = new AdminRolePO()
                .setAdminRoleName(adminRoleDTO.getAdminRoleName())
                .setAdminRoleText(adminRoleDTO.getAdminRoleText());
        boolean flag = this.adminRoleService.save(adminRolePO);

        //若flag为true
        if(flag){
            log.debug("【END】 - function AdminRoleController - addRole , 新增角色成功 , 新增的角色信息为：" + adminRolePO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.error("【ERROR】 - function AdminRoleController - addRole , 新增角色成功");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "adminRoleName", value = "角色名", dataType = "string"),
        @ApiImplicitParam(name = "adminRoleText", value = "角色介绍", dataType = "int"),
    })
    @LogAnnotation(
        menuName = LogConstant.ADMIN_ROLE_MENU_NAME,
        action = LogConstant.UPDATE,
        operation = LogConstant.OPERATION_ADMIN_ROLE_UPDATE)
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(AdminRoleDTO.Update.class) AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("【START】 - function AdminRoleController - update");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminRoleController - update 参数校验失败");
            return check;
        }

        //判断当前角色名是否存在
        int count = this.adminRoleService.count(new QueryWrapper<>(new AdminRolePO()
                .setAdminRoleName(adminRoleDTO.getAdminRoleName())));
        if(count > 0){
            log.error("【ERROR】 - function AdminRoleController - update , 角色【{}】已经存在了",adminRoleDTO.getAdminRoleName());
            return ResponseResult.Success(ResultCodeEnums.CHECK_005);
        }

        AdminRolePO adminRolePO = new AdminRolePO()
                .setAdminRoleId(adminRoleDTO.getAdminRoleId())
                .setAdminRoleName(adminRoleDTO.getAdminRoleName())
                .setAdminRoleText(adminRoleDTO.getAdminRoleText())
                .setUpdateTime(DateUtil.getTime());
        boolean flag = this.adminRoleService.updateById(adminRolePO);

        //若flag为true
        if(flag){
            log.debug("【END】 - function AdminRoleController - update , 更新角色成功，更新的角色信息为：" + adminRolePO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("【ERROR】 - function AdminRoleController - update 更新角色失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "禁用/启用角色")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态 0：禁用 1：启用", required = true, dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.ADMIN_ROLE_MENU_NAME,
            action = LogConstant.DISABLE,
            operation = LogConstant.OPERATION_ADMIN_ROLE_DISABLE)
    @RsaAnnotation
    @RequestMapping(value = "/disableRole" , method = RequestMethod.POST)
    public JsonResult disableRole(@RequestBody @Validated(AdminRoleDTO.Disable.class) AdminRoleDTO adminRoleDTO , BindingResult br){
        log.debug("【START】 - function AdminRoleController - disableRole");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminRoleController - disableRole 参数校验失败");
            return check;
        }

        int count = this.adminUserRoleService.count(new QueryWrapper<>(new AdminUserRolePO()
                .setAdminRoleId(adminRoleDTO.getAdminRoleId())));
        if(count > 0){
            log.error("【ERROR】 - function AdminRoleController - disableRole , 角色【{}】正在使用，无法禁用",adminRoleDTO.getAdminRoleId());
            return ResponseResult.Success(ResultCodeEnums.CHECK_006);
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
        log.error("【ERROR】 - function AdminRoleController - disableRole , 禁用/启用角色成功");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

}
