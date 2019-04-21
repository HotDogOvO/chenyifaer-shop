package com.chenyifaer.back.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.AdminMenuDTO;
import com.chenyifaer.back.entity.po.AdminMenuPO;
import com.chenyifaer.back.entity.vo.AdminMenuVO;
import com.chenyifaer.back.service.AdminMenuService;
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

import java.util.List;

/**
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 权限管理 - 后台菜单表 前端控制器
 * @author wudh
 * @since 2019-04-06
 */
@Slf4j
@RestController
@RequestMapping("/admin/menu")
@Api(value = "账号管理",tags = {"账号管理 - 后台菜单管理"})
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    @ApiOperation(value = "查询菜单列表")
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function AdminMenuController - list ");

        List<AdminMenuVO> list = this.adminMenuService.getList();
        log.debug("【END】 - function AdminMenuController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "查询父菜单")
    @RsaAnnotation
    @RequestMapping(value = "/getParentMenu" , method = RequestMethod.POST)
    public JsonResult getParentMenu(){
        log.debug("【START】 - function AdminMenuController - getParentMenu ");
        List<AdminMenuPO> list = this.adminMenuService.list(new QueryWrapper<>(new AdminMenuPO().setAdminMenuParentId(0)));
        log.debug("【END】 - function AdminMenuController - getParentMenu 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "根据ID查询菜单详情")
    @RsaAnnotation
    @RequestMapping(value = "/getMenuById" , method = RequestMethod.POST)
    public JsonResult getMenuById(@RequestBody @Validated(AdminMenuDTO.GetOne.class) AdminMenuDTO adminMenuDTO , BindingResult br){
        log.debug("【START】 - function AdminMenuController - getMenuById ");

        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminMenuController - getMenuById 参数校验失败");
            return check;
        }

        List<AdminMenuPO> list = this.adminMenuService.list(new QueryWrapper<>(new AdminMenuPO().setAdminMenuId(adminMenuDTO.getAdminMenuId())));
        log.debug("【END】 - function AdminMenuController - getMenuById 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "新增菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminMenuParentId", value = "父节点ID", dataType = "int"),
        @ApiImplicitParam(name = "adminMenuName", value = "菜单名", required = true, dataType = "string"),
        @ApiImplicitParam(name = "icon", value = "图标路径", dataType = "string"),
        @ApiImplicitParam(name = "url", value = "菜单路由", required = true, dataType = "string"),
        @ApiImplicitParam(name = "weight", value = "权重", required = true, dataType = "int"),
    })
    @LogAnnotation(
        menuName = LogConstant.ADMIN_MENU_MENU_NAME,
        action = LogConstant.ADD,
        operation = LogConstant.OPERATION_ADMIN_MENU_ADD)
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(AdminMenuDTO.Add.class) AdminMenuDTO adminMenuDTO , BindingResult br){
        log.debug("【START】 - function AdminMenuController - add");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminMenuController - add 参数校验失败");
            return check;
        }

        AdminMenuPO adminMenuPO = new AdminMenuPO()
                .setAdminMenuName(adminMenuDTO.getAdminMenuName())
                .setAdminMenuParentId(adminMenuDTO.getAdminMenuParentId())
                .setIcon(adminMenuDTO.getIcon())
                .setUrl(adminMenuDTO.getUrl())
                .setWeight(adminMenuDTO.getWeight());
        boolean flag = this.adminMenuService.save(adminMenuPO);

        //若flag为true
        if(flag){
            log.debug("【END】 - function AdminMenuController - add , 菜单新增成功，新增的数据为：" + adminMenuPO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.error("【ERROR】 - function AdminMenuController - add , 菜单新增失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminMenuId", value = "主键", required = true, dataType = "int"),
        @ApiImplicitParam(name = "adminMenuParentId", value = "父节点ID", dataType = "int"),
        @ApiImplicitParam(name = "adminMenuName", value = "菜单名", dataType = "string"),
        @ApiImplicitParam(name = "icon", value = "图标路径", dataType = "string"),
        @ApiImplicitParam(name = "url", value = "菜单路由", dataType = "string"),
        @ApiImplicitParam(name = "weight", value = "权重", dataType = "int"),
    })
    @LogAnnotation(
        menuName = LogConstant.ADMIN_MENU_MENU_NAME,
        action = LogConstant.UPDATE,
        operation = LogConstant.OPERATION_ADMIN_MENU_UPDATE)
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(AdminMenuDTO.Update.class) AdminMenuDTO adminMenuDTO , BindingResult br){
        log.debug("【START】 - function AdminMenuController - update");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminMenuController - update 参数校验失败");
            return check;
        }

        //如果传递的父ID与本身主键重复，则返回失败
        if(adminMenuDTO.getAdminMenuParentId() != null && adminMenuDTO.getAdminMenuParentId().equals(adminMenuDTO.getAdminMenuId())){
            log.error("【ERROR】 - function AdminMenuController - update 传递的主键与父节点重复");
            return ResponseResult.Fail(ResultCodeEnums.CHECK_002);
        }

        AdminMenuPO adminMenuPO = new AdminMenuPO()
                .setAdminMenuId(adminMenuDTO.getAdminMenuId())
                .setAdminMenuName(adminMenuDTO.getAdminMenuName())
                .setAdminMenuParentId(adminMenuDTO.getAdminMenuParentId())
                .setIcon(adminMenuDTO.getIcon())
                .setUrl(adminMenuDTO.getUrl())
                .setWeight(adminMenuDTO.getWeight())
                .setUpdateTime(DateUtil.getTime());
        boolean flag = this.adminMenuService.updateById(adminMenuPO);

        //若flag为true
        if(flag){
            log.debug("【END】 - function AdminMenuController - update , 菜单更新成功，更新的数据为：" + adminMenuPO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("【ERROR】 - function AdminMenuController - update , 菜单更新失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminMenuId", value = "主键", required = true, dataType = "int"),
    })
    @LogAnnotation(
        menuName = LogConstant.ADMIN_MENU_MENU_NAME,
        action = LogConstant.DELETE,
        operation = LogConstant.OPERATION_ADMIN_MENU_DELETE)
    @RsaAnnotation
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    public JsonResult delete(@RequestBody @Validated(AdminMenuDTO.Delete.class) AdminMenuDTO adminMenuDTO , BindingResult br) {
        log.debug("【START】 - function AdminMenuController - delete");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AdminMenuController - delete 参数校验失败");
            return check;
        }

        int count = this.adminMenuService.getCount(adminMenuDTO.getAdminMenuId());
        if(count > 0){
            log.error("【ERROR】 - function AdminMenuController - delete , 菜单删除失败，菜单下拥有子菜单");
            return ResponseResult.Fail(ResultCodeEnums.CHECK_003);
        }

        boolean flag = this.adminMenuService.remove(new UpdateWrapper(
                new AdminMenuPO().setAdminMenuId(adminMenuDTO.getAdminMenuId())));

        //若flag为true
        if(flag){
            log.debug("【END】 - function AdminMenuController - delete , 菜单删除成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_004);
        }
        log.error("【ERROR】 - function AdminMenuController - delete , 菜单删除失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10004);
    }

}