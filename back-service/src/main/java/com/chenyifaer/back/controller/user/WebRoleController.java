package com.chenyifaer.back.controller.user;


import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.po.WebRolePO;
import com.chenyifaer.back.entity.vo.WebRoleNameVO;
import com.chenyifaer.back.service.WebRoleService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前台角色管理 - 前台角色表 前端控制器
 * </p>
 *
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

    @ApiOperation(value = "查询角色下拉框")
    @RsaAnnotation
    @RequestMapping(value = "/getRoleName" , method = RequestMethod.POST)
    public JsonResult getRoleName(){
        log.debug("function start WebRoleController - getRoleName");
        List<WebRolePO> list = this.webRoleService.list();
        List<WebRoleNameVO> roleList = new ArrayList<WebRoleNameVO>();
        list.forEach(x ->{
            roleList.add(new WebRoleNameVO()
                    .setRoleName(x.getRoleName())
                    .setRoleId(x.getRoleId()));
        });
        log.debug("function end WebRoleController - getRoleName 返回的结果为：" + roleList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,roleList);
    }
    
}
