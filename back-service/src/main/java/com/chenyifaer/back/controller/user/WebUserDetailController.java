package com.chenyifaer.back.controller.user;


import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.WebUserDetailDTO;
import com.chenyifaer.back.entity.vo.WebUserDetailVO;
import com.chenyifaer.back.service.WebUserDetailService;
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
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 前台账号管理 - 前台用户详情表 前端控制器
 * @author wudh
 * @since 2019-04-18
 */
@Slf4j
@RestController
@RequestMapping("/web/userDetail")
@Api(value = "用户管理",tags = {"用户管理 - 用户管理"})
public class WebUserDetailController {

    @Autowired
    private WebUserDetailService webUserDetailService;

    @ApiOperation(value = "查詢用戶詳情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用戶ID", required = true, paramType = "query", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated WebUserDetailDTO webUserDetailDTO , BindingResult br) {
        log.debug("function start WebUserDetailController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("function WebUserDetailController - list 参数校验失败");
            return check;
        }
        List<WebUserDetailVO> list = this.webUserDetailService.getUserDetail(webUserDetailDTO);
        log.debug("function end WebUserDetailController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }
    
}
