package com.chenyifaer.web.controller.user;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.po.WebUserPO;
import com.chenyifaer.web.service.WebUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 前台账号管理 - 前台用户表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = {"用户管理 - 用户管理"})
public class WebUserController {
    
    @Autowired
    private WebUserService webUserService;

    @ApiOperation(value = "查询用户")
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function WebUserController - list ");

        List<WebUserPO> list = this.webUserService.list();
        log.debug("【END】 - function WebUserController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }

}
