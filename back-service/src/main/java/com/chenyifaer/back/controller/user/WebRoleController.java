package com.chenyifaer.back.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.WebRoleDTO;
import com.chenyifaer.back.entity.po.WebRolePO;
import com.chenyifaer.back.entity.vo.WebRoleNameVO;
import com.chenyifaer.back.service.WebRoleService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import io.swagger.annotations.Api;
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
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 前台角色管理 - 前台角色表 前端控制器
 * @author wudh
 * @since 2019-04-18
 */
@Slf4j
@RestController
@RequestMapping("/web/role")
@Api(value = "用户管理",tags = {"用户管理 - 角色管理"})
public class WebRoleController {

    @Autowired
    private WebRoleService webRoleService;

    @ApiOperation(value = "查询角色列表")
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated WebRoleDTO webRoleDTO , BindingResult br){
        log.debug("【START】 - function WebRoleController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【END】 - function WebRoleController - list 参数校验失败");
            return check;
        }

        List<WebRolePO> list = this.webRoleService.list(new QueryWrapper<>(new WebRolePO()
                .setRoleName(webRoleDTO.getRoleName())
                .setStatus(webRoleDTO.getStatus())));
        log.debug("【END】 - function END WebRoleController - list 返回的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

    @ApiOperation(value = "查询角色下拉框")
    @RsaAnnotation
    @RequestMapping(value = "/getRoleName" , method = RequestMethod.POST)
    public JsonResult getRoleName(){
        log.debug("【START】 - function WebRoleController - getRoleName");
        List<WebRolePO> list = this.webRoleService.list();
        List<WebRoleNameVO> roleList = new ArrayList<WebRoleNameVO>();
        list.forEach(x ->{
            roleList.add(new WebRoleNameVO()
                    .setRoleName(x.getRoleName())
                    .setRoleId(x.getRoleId()));
        });
        log.debug("【END】 - function WebRoleController - getRoleName 返回的结果为：" + roleList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,roleList);
    }

    @ApiOperation(value = "新增角色")
    @RsaAnnotation
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(WebRoleDTO.Add.class) WebRoleDTO webRoleDTO , BindingResult br){
        log.debug("【START】 - function WebRoleController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebRoleController - add 参数校验失败");
            return check;
        }

        boolean flag = this.webRoleService.save(new WebRolePO()
                .setRoleName(webRoleDTO.getRoleName())
                .setRoleText(webRoleDTO.getRoleText()));

        //若flag为true
        if(flag){
            log.debug("【END】 - function WebRoleController - add , 新增角色成功 , 新增的角色信息为：" + webRoleDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
        }
        log.error("【ERROR】 - function WebRoleController - add , 新增角色失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新角色")
    @RsaAnnotation
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(WebRoleDTO.Update.class) WebRoleDTO webRoleDTO , BindingResult br){
        log.debug("【START】 - function WebRoleController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebRoleController - update 参数校验失败");
            return check;
        }

        boolean flag = this.webRoleService.updateById(new WebRolePO()
                .setRoleId(webRoleDTO.getRoleId())
                .setRoleName(webRoleDTO.getRoleName())
                .setRoleText(webRoleDTO.getRoleText())
                .setUpdateTime(DateUtil.getTime()));

        //若flag为true
        if(flag){
            log.debug("【END】 - function WebRoleController - update , 更新角色成功 , 更新的角色信息为：" + webRoleDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("【ERROR】 - function WebRoleController - update , 更新角色成功");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "禁用/启用角色")
    @RsaAnnotation
    @RequestMapping(value = "/disable" , method = RequestMethod.POST)
    public JsonResult disable(@RequestBody @Validated(WebRoleDTO.Disable.class) WebRoleDTO webRoleDTO , BindingResult br){
        log.debug("【START】 - function WebRoleController - disable");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function WebRoleController - disable 参数校验失败");
            return check;
        }

        boolean flag = this.webRoleService.save(new WebRolePO()
                .setStatus(webRoleDTO.getStatus())
                .setUpdateTime(DateUtil.getTime()));

        //若flag为true
        if(flag){
            log.debug("【END】 - function WebRoleController - disable , 禁用/启用角色成功 , 禁用的角色信息为：" + webRoleDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("【ERROR】 - function WebRoleController - disable , 禁用/启用角色成功");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }
    
}
