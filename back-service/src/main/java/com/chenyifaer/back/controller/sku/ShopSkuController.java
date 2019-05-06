package com.chenyifaer.back.controller.sku;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.entity.dto.GoodsDTO;
import com.chenyifaer.back.entity.po.ShopSkuPO;
import com.chenyifaer.back.entity.vo.GoodsDetailVO;
import com.chenyifaer.back.entity.vo.GoodsSkuTreeVO;
import com.chenyifaer.back.service.ShopSkuService;
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
 * 分类管理 - sku分类属性表 前端控制器
 * @author wudh
 * @since 2019-04-30
 */

@Slf4j
@RestController
@RequestMapping("/goods/sku")
@Api(value = "商品管理",tags = {"商品管理 - 商品SKU管理"})
public class ShopSkuController {

    @Autowired
    private ShopSkuService shopSkuService;

    @ApiOperation(value = "查询商品模块SKU树状图")
    @RsaAnnotation
    @RequestMapping(value = "/getGoodsSkuTree" , method = RequestMethod.POST)
    public JsonResult getGoodsSkuTree() {
        log.debug("【START】 - function ShopSkuController - list");
        List<GoodsSkuTreeVO> list = this.shopSkuService.getGoodsSkuTree();
        log.debug("【END】 - function end ShopSkuController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

}
