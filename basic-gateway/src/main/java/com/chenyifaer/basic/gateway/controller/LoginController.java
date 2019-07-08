package com.chenyifaer.basic.gateway.controller;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.constant.OauthClientConstant;
import com.chenyifaer.basic.common.emuns.OauthUserTypeEnum;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.basic.common.util.StringUtil;
import com.chenyifaer.basic.gateway.annotation.RsaAnnotation;
import com.chenyifaer.basic.gateway.entity.dto.AdminLoginDTO;
import com.chenyifaer.basic.gateway.entity.dto.AdminUserMenuDTO;
import com.chenyifaer.basic.gateway.feign.AdminUserFeign;
import com.chenyifaer.basic.gateway.feign.Oauth2Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
 * 系统登录
 * @Author:wudh
 * @Date: 2019/4/13 17:14
 */
@Slf4j
@RestController
@RequestMapping("/sys")
@Api(value = "系统登录",tags = {"系统登录 - 系统登录"})
public class LoginController {

    @Autowired
    private Oauth2Client oauth2Client;

    @Autowired
    private AdminUserFeign adminUserFeign;

    @ApiOperation(value = "后台登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "adminUserAccount", value = "账号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "adminUserPassword", value = "密码", required = true, dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public JsonResult login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.debug("[START] - function LoginController - login");

        //进行outh2.0验证
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, OauthClientConstant.CLIENT_ID);
        parameters.put("client_secret", OauthClientConstant.CLIENT_SECRET);
        parameters.put(OAuth2Utils.SCOPE, OauthClientConstant.CLIENT_SCOPE);
        // 为了支持多类型登录，这里在username后拼装上登录类型
        parameters.put("username", adminLoginDTO.getAdminUserAccount() + "|" + OauthUserTypeEnum.USERNAME.name());
        parameters.put("password", adminLoginDTO.getAdminUserPassword());

        //获取Oauth返回的Token信息
        Map<String, Object> tokenInfo = oauth2Client.postAccessToken(parameters);

        //获取当前用户登录的权限
        JsonResult jsonResult = this.adminUserFeign.getUserMenuList(new AdminUserMenuDTO()
                .setAdminUserAccount(adminLoginDTO.getAdminUserAccount()));

        Map<String,Object> map = new HashMap<>();
        map.put("list",jsonResult.getData());
        map.put("tokenInfo",tokenInfo);

        log.debug("[END] - function LoginController - login");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_LOGIN, map);
    }

    /**
     * 退出
     */
    @ApiOperation(value = "系统退出")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "access_token", value = "access_token", required = true, dataType = "string"),
    })
    @GetMapping("/sys/logout")
    public void logout(String access_token, @RequestHeader(required = false, value = "Authorization") String token) {
        log.debug("[START] - function logout");
        if (StringUtil.isNullOrEmpty(access_token)) {
            if (!StringUtil.isNullOrEmpty(token)) {
                access_token = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
        log.debug("[END] - function logout");
        oauth2Client.removeToken(access_token);
    }

}
