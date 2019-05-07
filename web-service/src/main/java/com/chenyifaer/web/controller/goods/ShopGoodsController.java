package com.chenyifaer.web.controller.goods;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.vo.GoodsRecommendedVO;
import com.chenyifaer.web.service.ShopGoodsService;
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
 * 商品管理 - 商品表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */

@Slf4j
@RestController
@RequestMapping("/goods")
@Api(value = "商品管理",tags = {"商品管理 - 商品管理"})
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @ApiOperation(value = "查询首页推荐商品")
    @RsaAnnotation
    @RequestMapping(value = "/getRecommendGoods" , method = RequestMethod.POST)
    public JsonResult getRecommendGoods() {
        log.debug("【START】 - function ShopGoodsController - getRecommendGoods");

        List<GoodsRecommendedVO> list = this.shopGoodsService.getRecommendedList();

        log.debug("【END】 - function end ShopGoodsController - getRecommendGoods");
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }
    
}
