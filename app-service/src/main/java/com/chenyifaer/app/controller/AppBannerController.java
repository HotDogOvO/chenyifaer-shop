package com.chenyifaer.app.controller;


import com.chenyifaer.app.entity.po.AppBannerPO;
import com.chenyifaer.app.entity.vo.BannerVO;
import com.chenyifaer.app.service.AppBannerService;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 小程序端 - 轮播图表 前端控制器
 * </p>
 *
 * @author wudh
 * @since 2019-07-08
 */
@Slf4j
@RestController
@RequestMapping("/banner")
@Api(value = "轮播图管理",tags = {"轮播图管理 - 轮播图管理"})
public class AppBannerController {

    @Autowired
    private AppBannerService appBannerService;
    
    @ApiOperation(value = "查询轮播图列表")
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function AppBannerController - list");
        List<AppBannerPO> list = this.appBannerService.list();
        List<BannerVO> bannerList = new ArrayList<>();
        list.stream().filter(x ->
            bannerList.add(new BannerVO()
                .setAppId(x.getAppId())
                .setAppType(x.getAppType())
                .setAppBannerImageUrl(x.getAppBannerImageUrl()))
        ).collect(Collectors.toList());
        log.debug("【END】 - function AppBannerController - list - 获取的数据为【{}】",bannerList);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS,bannerList);
    }
    
}
