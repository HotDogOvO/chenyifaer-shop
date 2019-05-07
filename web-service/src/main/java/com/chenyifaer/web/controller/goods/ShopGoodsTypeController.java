package com.chenyifaer.web.controller.goods;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.vo.GoodsTypeVO;
import com.chenyifaer.web.service.ShopGoodsTypeService;
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
 * 分类管理 - 商品分类表 前端控制器
 * @author wudh
 * @since 2019-05-07
 */

@Slf4j
@RestController
@RequestMapping("/goods/type")
@Api(value = "商品分类管理",tags = {"商品分类管理 - 商品分类管理"})
public class ShopGoodsTypeController {

    @Autowired
    private ShopGoodsTypeService shopGoodsTypeService;

    @ApiOperation(value = "查询分类选项卡")
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(){
        log.debug("【START】 - function ShopGoodsTypeController - list");
        List<GoodsTypeVO> list = this.shopGoodsTypeService.getList();
        log.debug("【END】 - function ShopGoodsTypeController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,list);
    }
    
}
