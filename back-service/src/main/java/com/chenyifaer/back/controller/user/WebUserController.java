package com.chenyifaer.back.controller.user;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.WebUserDTO;
import com.chenyifaer.back.entity.po.WebUserPO;
import com.chenyifaer.back.entity.vo.WebUserVO;
import com.chenyifaer.back.enums.UserSexEnum;
import com.chenyifaer.back.enums.UserStatusEnum;
import com.chenyifaer.back.enums.UserTypeEnum;
import com.chenyifaer.back.service.WebUserService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.exception.ExportException;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * _____ _            __     ___ ______                ________ ____ ______ ____
 * / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 * | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 * | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 * | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 * \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 */

/**
 * 前台账号管理 - 前台用户表 前端控制器
 * @author wudh
 * @since 2019-04-18
 */

@Slf4j
@RestController
@RequestMapping("/web/user")
@Api(value = "用户管理", tags = {"用户管理 - 用户管理"})
public class WebUserController {

    @Autowired
    private WebUserService webUserService;

    @ApiOperation(value = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userAccount", value = "账号", dataType = "string"),
            @ApiImplicitParam(name = "userNickName", value = "昵称", dataType = "string"),
            @ApiImplicitParam(name = "userType", value = "用户关注渠道 （1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户）", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "用户状态 （0：禁用 1：启用 9：注销）", dataType = "string"),
            @ApiImplicitParam(name = "userSex", value = "用户性别（0：保密 1：男 2：女）", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色ID", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string"),
    })
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated WebUserDTO webUserDTO, BindingResult br) {
        log.debug("function start WebUserController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("function WebUserController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(webUserDTO.getPageIndex(), webUserDTO.getPageSize());
        List<WebUserVO> list = this.webUserService.getList(webUserDTO);
        PageInfo<WebUserVO> pageList = new PageInfo<>(list);
        log.debug("function end WebUserController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "禁用/启用用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态 0：禁用 1：启用", required = true, dataType = "int"),
    })
    @LogAnnotation(
            menuName = LogConstant.USER_MENU_NAME,
            action = LogConstant.DISABLE,
            operation = LogConstant.OPERATION_USER_DISABLE)
    @RsaAnnotation
    @RequestMapping(value = "/disableUser", method = RequestMethod.POST)
    public JsonResult disableUser(@RequestBody @Validated(WebUserDTO.Add.class) WebUserDTO webUserDTO, BindingResult br) {
        log.debug("function start WebUserController - disableUser");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("function WebUserController - disableUser 参数校验失败");
            return check;
        }
        WebUserPO webUserPO = new WebUserPO()
                .setUserId(webUserDTO.getUserId())
                .setStatus(webUserDTO.getStatus())
                .setUpdateTime(DateUtil.getTime());
        boolean flag = this.webUserService.updateById(webUserPO);

        //若flag为true
        if (flag) {
            log.debug("function end WebUserController - disableUser , 禁用/启用用户成功 , 禁用的用户信息为：" + webUserPO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.error("function WebUserController - disableUser , 禁用/启用用户失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "导出数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "userAccount", value = "账号", dataType = "string"),
            @ApiImplicitParam(name = "userNickName", value = "昵称", dataType = "string"),
            @ApiImplicitParam(name = "userType", value = "用户关注渠道 （1：注册 2：微信用户 3：QQ用户 4：支付宝用户 5：微博用户）", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "用户状态 （0：禁用 1：启用 9：注销）", dataType = "string"),
            @ApiImplicitParam(name = "userSex", value = "用户性别（0：保密 1：男 2：女）", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色ID", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.USER_MENU_NAME,
            action = LogConstant.EXPORT,
            operation = LogConstant.OPERATION_USER_EXPORT)
    @RsaAnnotation
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestBody @Validated WebUserDTO webUserDTO, HttpServletResponse response) {
        log.debug("function start WebUserController - export");
        try {
            this.webUserService.export(webUserDTO, response);
        } catch (ExportException e) {
            e.printStackTrace();
        }
        log.debug("function end WebUserController - export");
    }

}
