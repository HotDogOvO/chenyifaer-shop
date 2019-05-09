package com.chenyifaer.web.controller.goods;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.ResponseResult;
import com.chenyifaer.web.annotation.RsaAnnotation;
import com.chenyifaer.web.entity.dto.GoodsTypeDTO;
import com.chenyifaer.web.entity.po.ShopGoodsTypePO;
import com.chenyifaer.web.entity.vo.GoodsTypeOneRankVO;
import com.chenyifaer.web.entity.vo.GoodsTypeThreeRankVO;
import com.chenyifaer.web.entity.vo.GoodsTypeVO;
import com.chenyifaer.web.service.ShopGoodsTypeService;
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

    @ApiOperation(value = "查询一级分类")
    @RsaAnnotation
    @RequestMapping(value = "/getOneRankType" , method = RequestMethod.POST)
    public JsonResult getOneRankType(){
        log.debug("【START】 - function ShopGoodsTypeController - getOneRankType");
        //查询所有一级分类
        List<ShopGoodsTypePO> list = this.shopGoodsTypeService.list(new QueryWrapper<>(
                new ShopGoodsTypePO().setRank(1)));

        List<GoodsTypeOneRankVO> typeList = new ArrayList<>();
        list.stream().filter(x ->
            typeList.add(new GoodsTypeOneRankVO()
                    .setTypeName(x.getTypeName())
                    .setGoodsTypeId(x.getShopGoodsTypeId()))
        ).collect(Collectors.toList());

        log.debug("【END】 - function ShopGoodsTypeController - getOneRankType 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001,typeList);
    }

    @ApiOperation(value = "根据一级分类查询三级分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "parentTypeId", value = "分类ID", dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/getTypeByParent" , method = RequestMethod.POST)
    public JsonResult getTypeByParent(@RequestBody @Validated(GoodsTypeDTO.getTypeById.class) GoodsTypeDTO goodsTypeDTO, BindingResult br) {
        log.debug("【START】 - function ShopGoodsTypeController - getTypeByParent");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsTypeController - getTypeByParent 参数校验失败");
            return check;
        }
        List<GoodsTypeThreeRankVO> list = this.shopGoodsTypeService.getThreeRankTypeByTypeId(goodsTypeDTO);
        log.debug("【END】 - function end ShopGoodsTypeController - getTypeByParent，查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, list);
    }
    
}
