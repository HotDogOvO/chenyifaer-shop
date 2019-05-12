package com.chenyifaer.web.controller.goods;


import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.dto.SkuDTO;
import com.chenyifaer.web.entity.vo.GoodsSkuVO;
import com.chenyifaer.web.entity.vo.SkuKeyVO;
import com.chenyifaer.web.entity.vo.SkuValueVO;
import com.chenyifaer.web.service.ShopGoodsSkuService;
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
 * 分类管理 - sku商品表 前端控制器
 * @author wudh
 * @since 2019-05-10
 */

@Slf4j
@RestController
@RequestMapping("/goods/sku")
@Api(value = "SKU管理",tags = {"商品管理 - SKU管理"})
public class ShopGoodsSkuController {
    
    @Autowired
    private ShopGoodsSkuService shopGoodsSkuService;

    @ApiOperation(value = "购物车页 - 根据商品ID查询对应的SKU")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "goodsId", value = "分类ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getSkuByGoodsId" , method = RequestMethod.POST)
    public JsonResult getSkuByGoodsId(@RequestBody @Validated(GoodsDTO.getSkuByGoods.class) GoodsDTO goodsDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsSkuController - getSkuByGoodsId");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsSkuController - getSkuByGoodsId 商品详情参数校验失败");
            return check;
        }
        List<GoodsSkuVO> list = this.shopGoodsSkuService.getSkuByGoodsId(goodsDTO);
        log.debug("【END】 - function end ShopGoodsSkuController - getSkuByGoodsId，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "商品搜索页 - 获取SkuKey")
    @RsaAnnotation
    @RequestMapping(value = "/getSkuKey" , method = RequestMethod.POST)
    public JsonResult getSkuKey() {
        log.debug("【START】 - function ShopGoodsSkuController - getSkuKey");
        List<SkuKeyVO> list = this.shopGoodsSkuService.getKeyName();
        log.debug("【END】 - function end ShopGoodsSkuController - getSkuKey，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

    @ApiOperation(value = "商品搜索页 - 根据SkuKey查询SkuValue")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "skuKeyId", value = "主键", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getValueByKey" , method = RequestMethod.POST)
    public JsonResult getValueByKey(@RequestBody @Validated(SkuDTO.getSkuValue.class) SkuDTO skuDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsSkuController - getValueByKey");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsSkuController - getValueByKey 商品详情参数校验失败");
            return check;
        }
        List<SkuValueVO> list = this.shopGoodsSkuService.getSkuValueBySkuKey(skuDTO);
        log.debug("【END】 - function end ShopGoodsSkuController - getValueByKey，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }

}
