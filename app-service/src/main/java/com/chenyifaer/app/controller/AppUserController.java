package com.chenyifaer.app.controller;

import com.chenyifaer.app.entity.dto.LoginDTO;
import com.chenyifaer.app.service.AppUserService;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * 小程序端 - 用户表 前端控制器
 * @author wudh
 * @since 2019-07-06
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = {"用户管理 - 用户管理"})
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "微信授权code", dataType = "string"),
        @ApiImplicitParam(name = "nickName", value = "昵称", dataType = "string"),
        @ApiImplicitParam(name = "avatarUrl", value = "头像url", dataType = "string"),
        @ApiImplicitParam(name = "country", value = "国家", dataType = "string"),
        @ApiImplicitParam(name = "province", value = "省", dataType = "string"),
        @ApiImplicitParam(name = "city", value = "市", dataType = "string"),
        @ApiImplicitParam(name = "gender", value = "性别（1：男 2：女）", dataType = "int"),
        @ApiImplicitParam(name = "language", value = "语言", dataType = "string"),
    })
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public JsonResult login(@RequestBody @Validated LoginDTO loginDTO , BindingResult br){
        log.debug("【START】 - function AppUserController - login");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AppUserController - login 参数校验失败");
            return check;
        }

        String openId = this.appUserService.login(loginDTO);
        //判断插入是否成功
        if(StringUtils.isEmpty(openId)){
            //等于空 代表插入失败
            log.error("【ERROR】 - function AppUserController - login 新增用户失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL);
        }
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,openId);
    }

}
