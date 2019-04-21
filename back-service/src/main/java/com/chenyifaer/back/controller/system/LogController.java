package com.chenyifaer.back.controller.system;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.LogDTO;
import com.chenyifaer.back.entity.vo.LogActionVO;
import com.chenyifaer.back.entity.vo.LogVO;
import com.chenyifaer.back.service.LogService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.exception.ExportException;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统管理 - 系统日志表 前端控制器
 * @author wudh
 * @since 2019-04-07
 */
@Slf4j
@RestController
@RequestMapping("/system/log")
@Api(value = "系统管理",tags = {"系统管理 - 系统日志管理"})
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "查询日志列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "adminUserName", value = "操作人姓名", dataType = "string"),
        @ApiImplicitParam(name = "action", value = "动作", dataType = "string"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated LogDTO logDTO , BindingResult br) {
        log.debug("【START】 - function LogController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function LogController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(logDTO.getPageIndex(), logDTO.getPageSize());
        List<LogVO> list = this.logService.getList(logDTO);
        PageInfo<LogVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function LogController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "查询操作下拉框")
    @RsaAnnotation
    @RequestMapping(value = "/getAction" , method = RequestMethod.POST)
    public JsonResult getAction() {
        log.debug("【START】 - function LogController - getAction");
        List<LogActionVO> list = this.logService.getAction();
        log.debug("【END】 - function LogController - getAction 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminUserName", value = "操作人姓名", dataType = "string"),
            @ApiImplicitParam(name = "action", value = "动作", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "string"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string"),
    })
    @LogAnnotation(
            menuName = LogConstant.LOG_MENU_NAME,
            action = LogConstant.EXPORT,
            operation = LogConstant.OPERATION_LOG_EXPORT)
    @RsaAnnotation
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestBody @Validated LogDTO logDTO, HttpServletResponse response) {
        log.debug("【START】 - function LogController - export");
        try {
            this.logService.export(logDTO, response);
        } catch (ExportException e) {
            e.printStackTrace();
        }
        log.debug("【END】 - function LogController - export");
    }


}