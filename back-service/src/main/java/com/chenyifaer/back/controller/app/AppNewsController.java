package com.chenyifaer.back.controller.app;


import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.AppNewsDTO;
import com.chenyifaer.back.entity.vo.AppNewsVO;
import com.chenyifaer.back.service.AppNewsService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
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
 * 小程序端 - 最新动态表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-12
 */
@Slf4j
@RestController
@RequestMapping("/app/news")
@Api(value = "小程序管理",tags = {"小程序管理 - 最新动态管理"})
public class AppNewsController {

    @Autowired
    private AppNewsService appNewsService;

    @ApiOperation(value = "查询最新动态列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "newsName", value = "标题", dataType = "string"),
        @ApiImplicitParam(name = "status", value = "状态", dataType = "int"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "string"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "string"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated AppNewsDTO appNewsDTO, BindingResult br){
        log.debug("【START】 - function AppNewsController - list");
        JsonResult check = CheckUtil.check(br);
        if(check != null){
            log.error("【ERROR】 - function AppNewsController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(appNewsDTO.getPageIndex(),appNewsDTO.getPageSize());
        List<AppNewsVO> list = this.appNewsService.getList(appNewsDTO);
        PageInfo<AppNewsVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function AppNewsController - list , 查询最新动态列表成功，查询的数据为：【{}】", list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS, pageList);
    }

}
