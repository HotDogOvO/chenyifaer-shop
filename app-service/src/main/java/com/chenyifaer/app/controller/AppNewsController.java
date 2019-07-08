package com.chenyifaer.app.controller;

import com.chenyifaer.app.entity.dto.NewsDTO;
import com.chenyifaer.app.entity.vo.NewsDetailVO;
import com.chenyifaer.app.entity.vo.NewsVO;
import com.chenyifaer.app.service.AppNewsService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
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
 * 小程序端 - 最新动态表 前端控制器
 * @author wudh
 * @since 2019-07-08
 */
@Slf4j
@RestController
@RequestMapping("/news")
@Api(value = "最新动态管理",tags = {"最新动态管理 - 最新动态管理"})
public class AppNewsController {

    @Autowired
    private AppNewsService appNewsService;

    @ApiOperation(value = "获取最新动态列表")
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function AppNewsController - list");

        List<NewsVO> list = this.appNewsService.getList();

        log.debug("【END】 - function AppNewsController - list - 获取的数据为【{}】",list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
    }

    @ApiOperation(value = "获取最新动态详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "newsId", value = "主键", dataType = "int"),
    })
    @RequestMapping(value = "/getDetail" , method = RequestMethod.POST)
    public JsonResult getDetail(@RequestBody NewsDTO newsDTO){
        log.debug("【START】 - function AppNewsController - getDetail");

        List<NewsDetailVO> list = this.appNewsService.getDetail(newsDTO);

        log.debug("【END】 - function AppNewsController - getDetail - 获取的数据为【{}】",list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,list);
    }

}
