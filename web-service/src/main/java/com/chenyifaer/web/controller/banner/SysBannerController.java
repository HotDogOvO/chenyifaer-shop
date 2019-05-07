package com.chenyifaer.web.controller.banner;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.po.SysBannerPO;
import com.chenyifaer.web.entity.vo.BannerVO;
import com.chenyifaer.web.service.SysBannerService;
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
 *     _____ _            __     ___ ______                ________ ____ ______ ____
 *	  / ____| |           \ \   / (_)  ____|              / /____  |___ \____  |___ \
 *	 | |    | |__   ___ _ _\ \_/ / _| |__ __ _  ___ _ __ / /_   / /  __) |  / /  __) |
 *	 | |    | '_ \ / _ \ '_ \   / | |  __/ _` |/ _ \ '__| '_ \ / /  |__ <  / /  |__ <
 *	 | |____| | | |  __/ | | | |  | | | | (_| |  __/ |  | (_) / /   ___) |/ /   ___) |
 *	  \_____|_| |_|\___|_| |_|_|  |_|_|  \__,_|\___|_|   \___/_/   |____//_/   |____/
 *
 */

/**
 * 系统管理 - 轮播图管理表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */

@Slf4j
@RestController
@RequestMapping("/banner")
@Api(value = "轮播图管理",tags = {"轮播图管理 - 轮播图管理"})
public class SysBannerController {

    @Autowired
    private SysBannerService sysBannerService;

    @ApiOperation(value = "查询轮播图")
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list() {
        log.debug("【START】 - function SysBannerController - list");
        List<SysBannerPO> list = this.sysBannerService.list();
        List<BannerVO> bannerList = new ArrayList<>();

        list.stream().filter(x ->
            bannerList.add(new BannerVO().setUrl(x.getBannerImageUrl()))
        ).collect(Collectors.toList());

        log.debug("【END】 - function end SysBannerController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, bannerList);
    }
    
}
