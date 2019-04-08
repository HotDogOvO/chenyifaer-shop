package com.chenyifaer.back.controller.login;

import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.AdminLoginDTO;
import com.chenyifaer.back.service.LoginService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emun.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 系统管理 - 登录 前端控制器
 * @Author:wudh
 * @Date: 2019/4/7 20:47
 */
@Slf4j
@RestController
@RequestMapping("/sys/login")
@Api(value = "系统管理",tags = {"系统管理 - 登录"})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminUserAccount", value = "账号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "adminUserPassword", value = "密码", required = true, dataType = "string"),
    })
    @LogAnnotation(
        menuName = LogConstant.SYSTEM_MENU_NAME,
        action = LogConstant.LOGIN,
        operation = LogConstant.OPERATION_SYSTEM_LOGIN)
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public JsonResult login(@RequestBody AdminLoginDTO adminLoginDTO){
        log.debug("function start LoginController - login");
        int count = this.loginService.login(adminLoginDTO);
        if(count > 0){
            log.debug("function end LoginController - 登录成功");
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_LOGIN);
        }
        log.error("function end LoginController - 登录失败，用户名或密码错误");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_11001);
    }

}
