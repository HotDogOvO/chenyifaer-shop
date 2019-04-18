package com.chenyifaer.back.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.AdminRolePermissionDTO;
import com.chenyifaer.back.entity.po.AdminPermissionPO;
import com.chenyifaer.back.entity.po.AdminRolePermissionPO;
import com.chenyifaer.back.service.AdminPermissionService;
import com.chenyifaer.back.service.AdminRolePermissionService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
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

import java.util.List;

/**
 * 角色管理 - 后台角色权限表 前端控制器
 * @author wudh
 * @since 2019-04-06
 */
@Slf4j
@RestController
@RequestMapping("/admin/rolePermission")
@Api(value = "账号管理",tags = {"账号管理 - 后台角色管理"})
public class AdminRolePermissionController {

    @Autowired
    private AdminPermissionService adminPermissionService;

    @Autowired
    private AdminRolePermissionService adminRolePermissionService;

    @ApiOperation(value = "查询角色拥有的权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminRoleId", value = "角色ID", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated(AdminRolePermissionDTO.Select.class) AdminRolePermissionDTO adminRolePermissionDTO , BindingResult br){
        log.debug("function start AdminRolePermissionController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("function AdminRolePermissionController - list 参数校验失败");
            return check;
        }
        List<AdminRolePermissionPO> list = this.adminRolePermissionService.list(new QueryWrapper<>(new AdminRolePermissionPO()
                .setAdminRoleId(adminRolePermissionDTO.getAdminRoleId())));
        log.debug("function end AdminRolePermissionController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "角色授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminRoleId", value = "角色ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "adminMenuList", value = "菜单ID集合", dataType = "list"),
            @ApiImplicitParam(name = "adminMenuId", value = "菜单ID", dataType = "int"),
            @ApiImplicitParam(name = "adminPermissionIdList", value = "权限ID集合", dataType = "list"),
    })
    @LogAnnotation(
            menuName = LogConstant.ADMIN_ROLE_MENU_NAME,
            action = LogConstant.PERMISSION,
            operation = LogConstant.OPERATION_ROLE_PERMISSION)
    @RsaAnnotation
    @RequestMapping(value = "/permission" , method = RequestMethod.POST)
    public JsonResult permission(@RequestBody @Validated(AdminRolePermissionDTO.Permission.class) AdminRolePermissionDTO adminRolePermissionDTO , BindingResult br){
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
            adminRolePermissionDTO.getAdminPermissionList().forEach(x -> {
                //查询权限对应的菜单ID
                AdminPermissionPO adminPermissionPO = this.adminPermissionService.getMenuIdByPermissionId(x);

                AdminRolePermissionPO adminRolePermissionPO = new AdminRolePermissionPO()
                        .setAdminRoleId(adminRolePermissionDTO.getAdminRoleId())
                        .setAdminPermissionId(x)
                        .setAdminMenuId(adminPermissionPO.getAdminMenuId());
                //插入权限表
                this.adminRolePermissionService.save(adminRolePermissionPO);
            });
        }
        log.debug("function end AdminRoleController - permission , 角色授权成功，授权的角色信息为：" + adminRolePermissionDTO);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
    }

}
