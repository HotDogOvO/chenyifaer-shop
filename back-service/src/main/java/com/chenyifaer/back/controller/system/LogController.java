package com.chenyifaer.back.controller.system;


import com.chenyifaer.back.entity.dto.LogDTO;
import com.chenyifaer.back.entity.vo.LogVO;
import com.chenyifaer.back.service.LogService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emun.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
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

import java.util.List;

/**
 * <p>
 * 系统管理 - 系统日志表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-04-07
 */
@Slf4j
@RestController
@RequestMapping("/sys/log")
@Api(value = "系统管理",tags = {"系统管理 - 系统日志管理"})
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "查询日志列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "用户ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "用户ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "adminUserName", value = "用户ID", dataType = "string"),
        @ApiImplicitParam(name = "action", value = "用户ID", dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "用户ID", dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "用户ID", dataType = "string"),
    })
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated LogDTO logDTO , BindingResult br) {
        log.debug("function start LogController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("function LogController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(logDTO.getPageIndex(), logDTO.getPageSize());
        List<LogVO> list = this.logService.getList(logDTO);
        PageInfo<LogVO> pageList = new PageInfo<>(list);
        log.debug("function end LogController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }


}