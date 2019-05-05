package com.chenyifaer.back.controller.goods;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.config.FilesConfig;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.GoodsDTO;
import com.chenyifaer.back.entity.po.ShopGoodsCheckPO;
import com.chenyifaer.back.entity.po.ShopGoodsImagesPO;
import com.chenyifaer.back.entity.po.ShopGoodsPO;
import com.chenyifaer.back.entity.vo.GoodsVO;
import com.chenyifaer.back.service.ShopGoodsCheckService;
import com.chenyifaer.back.service.ShopGoodsImagesService;
import com.chenyifaer.back.service.ShopGoodsService;
import com.chenyifaer.back.util.FileUploadUtil;
import com.chenyifaer.basic.common.constant.JsonResult;
import com.chenyifaer.basic.common.emuns.FileUploadUrlEnum;
import com.chenyifaer.basic.common.emuns.ResultCodeEnums;
import com.chenyifaer.basic.common.util.CheckUtil;
import com.chenyifaer.basic.common.util.DateUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 * @since 2019-04-25
 */

@Slf4j
@RestController
@RequestMapping("/goods")
@Api(value = "商品管理",tags = {"商品管理 - 商品管理"})
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopGoodsImagesService shopGoodsImagesService;

    @Autowired
    private ShopGoodsCheckService shopGoodsCheckService;

    @Autowired
    private FilesConfig filesConfig;

    @ApiOperation(value = "查询商品列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名", required = false, dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：上架 2：下架 9：审核失败）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "recommendedStatus", value = "是否推荐（0：否 1：是）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "integralStatus", value = "是否支持积分（0：否 1：是）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "couponsStatus", value = "是否支持优惠券（0：否 1：是）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "typeName", value = "商品类别", required = false, dataType = "int"),
        @ApiImplicitParam(name = "startTime", value = "起始时间", required = false, dataType = "int"),
        @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated GoodsDTO goodsDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(goodsDTO.getPageIndex(), goodsDTO.getPageSize());
        List<GoodsVO> list = this.shopGoodsService.getList(goodsDTO);
        PageInfo<GoodsVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function end ShopGoodsController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "新增商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "goodsTypeId", value = "商品分类ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名", required = true, dataType = "string"),
        @ApiImplicitParam(name = "goodsText", value = "商品简介", required = true, dataType = "string"),
        @ApiImplicitParam(name = "goodsContent", value = "商品详情", required = true, dataType = "string"),
        @ApiImplicitParam(name = "url", value = "商品图片路径", required = true, dataType = "string"),
        @ApiImplicitParam(name = "goodsPrice", value = "商品价格", required = true, dataType = "BigDecimal"),
        @ApiImplicitParam(name = "goodsDiscount", value = "商品折扣", required = false, dataType = "string"),
        @ApiImplicitParam(name = "goodsDiscountPrice", value = "商品折扣价", required = false, dataType = "BigDecimal"),
        @ApiImplicitParam(name = "goodsInventory", value = "库存", required = true, dataType = "int"),
        @ApiImplicitParam(name = "recommendedStatus", value = "是否推荐（0：否 1：是）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "integralStatus", value = "是否支持积分（0：否 1：是）", required = false, dataType = "int"),
        @ApiImplicitParam(name = "couponsStatus", value = "是否支持优惠券（0：否 1：是）", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_MENU_NAME,
            action = LogConstant.ADD,
            operation = LogConstant.OPERATION_GOODS_ADD)
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public JsonResult add(@RequestBody @Validated(GoodsDTO.Add.class) GoodsDTO goodsDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - add");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - add 参数校验失败");
            return check;
        }

        ShopGoodsPO shopGoodsPO = new ShopGoodsPO()
                .setGoodsTypeId(goodsDTO.getGoodsTypeId())
                .setGoodsName(goodsDTO.getGoodsName())
                .setGoodsText(goodsDTO.getGoodsText())
                .setGoodsContent(goodsDTO.getGoodsContent())
                .setGoodsPrice(goodsDTO.getGoodsPrice())
                .setGoodsDiscount(goodsDTO.getGoodsDiscount())
                .setGoodsDiscountPrice(goodsDTO.getGoodsDiscountPrice())
                .setGoodsInventory(goodsDTO.getGoodsInventory())
                .setRecommendedStatus(goodsDTO.getRecommendedStatus())
                .setIntegralStatus(goodsDTO.getIntegralStatus())
                .setCouponsStatus(goodsDTO.getCouponsStatus());

        boolean flag = this.shopGoodsService.save(shopGoodsPO);

        if(flag){
            //插入商品图片表
            flag = this.shopGoodsImagesService.save(new ShopGoodsImagesPO()
                    .setGoodsId(shopGoodsPO.getGoodsId())
                    .setUrl(goodsDTO.getUrl())
                    //TODO 商品图片类型待修改
                    .setType(1));
            if(flag){
                //插入商品审核表
                flag = this.shopGoodsCheckService.save(new ShopGoodsCheckPO()
                        .setGoodsId(shopGoodsPO.getGoodsId()));
                if(flag){
                    log.debug("【END】 - function end ShopGoodsController - add 新增商品成功，新增的商品为：" + goodsDTO);
                    return ResponseResult.Success(ResultCodeEnums.SUCCESS_002);
                }
                log.error("【END】 - function end ShopGoodsController - add 新增商品审核失败");
                return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
            }
            log.error("【END】 - function end ShopGoodsController - add 新增商品图片失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
        }
        log.error("【END】 - function end ShopGoodsController - add 新增商品失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10002);
    }

    @ApiOperation(value = "更新商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "goodsTypeId", value = "商品分类ID", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsName", value = "商品名", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsText", value = "商品简介", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsContent", value = "商品详情", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsPrice", value = "商品价格", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsDiscount", value = "商品折扣", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsDiscountPrice", value = "商品折扣价", required = false, dataType = "int"),
            @ApiImplicitParam(name = "goodsInventory", value = "库存", required = false, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：上架 2：下架 9：审核失败）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "recommendedStatus", value = "是否推荐（0：否 1：是）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "integralStatus", value = "是否支持积分（0：否 1：是）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "couponsStatus", value = "是否支持优惠券（0：否 1：是）", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_GOODS_UPDATE)
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public JsonResult update(@RequestBody @Validated(GoodsDTO.Update.class) GoodsDTO goodsDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - update");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - update 参数校验失败");
            return check;
        }

        ShopGoodsPO shopGoodsPO = new ShopGoodsPO()
                .setGoodsId(goodsDTO.getGoodsId())
                .setGoodsTypeId(goodsDTO.getGoodsTypeId())
                .setGoodsName(goodsDTO.getGoodsName())
                .setGoodsText(goodsDTO.getGoodsText())
                .setGoodsContent(goodsDTO.getGoodsContent())
                .setGoodsPrice(goodsDTO.getGoodsPrice())
                .setGoodsDiscount(goodsDTO.getGoodsDiscount())
                .setGoodsDiscountPrice(goodsDTO.getGoodsDiscountPrice())
                .setGoodsInventory(goodsDTO.getGoodsInventory())
                .setRecommendedStatus(goodsDTO.getRecommendedStatus())
                .setIntegralStatus(goodsDTO.getIntegralStatus())
                .setCouponsStatus(goodsDTO.getCouponsStatus())
                .setUpdateTime(DateUtil.getTime());

        boolean flag = this.shopGoodsService.updateById(shopGoodsPO);

        if(flag){
            flag = this.shopGoodsImagesService.update(new ShopGoodsImagesPO().setUrl(goodsDTO.getUrl()),
                    //更新条件
                    new QueryWrapper<>(new ShopGoodsImagesPO().setGoodsId(goodsDTO.getGoodsId())));
            if(flag){
                log.debug("【END】 - function end ShopGoodsController - update 更新商品成功，更新内容为："+ goodsDTO);
                return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
            }
            log.debug("【END】 - function end ShopGoodsController - update 更新商品失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
        }

        log.debug("【END】 - function end ShopGoodsController - update 更新商品失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：上架  2：下架 9：审核失败）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "recommendedStatus", value = "是否推荐（0：否 1：是）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "integralStatus", value = "是否支持积分（0：否 1：是）", required = false, dataType = "int"),
            @ApiImplicitParam(name = "couponsStatus", value = "是否支持优惠券（0：否 1：是）", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_MENU_NAME,
            action = LogConstant.UPDATE,
            operation = LogConstant.OPERATION_GOODS_UPDATE)
    @RequestMapping(value = "/updateStatus" , method = RequestMethod.POST)
    public JsonResult updateStatus(@RequestBody @Validated(GoodsDTO.Update.class) GoodsDTO goodsDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - updateStatus");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - updateStatus 参数校验失败");
            return check;
        }

        ShopGoodsPO shopGoodsPO = new ShopGoodsPO()
                .setGoodsId(goodsDTO.getGoodsId())
                .setRecommendedStatus(goodsDTO.getRecommendedStatus())
                .setIntegralStatus(goodsDTO.getIntegralStatus())
                .setCouponsStatus(goodsDTO.getCouponsStatus())
                .setUpdateTime(DateUtil.getTime());

        boolean flag = this.shopGoodsService.updateById(shopGoodsPO);
        if(flag){
            log.debug("【END】 - function end ShopGoodsController - updateStatus 更新商品成功，更新内容为："+ goodsDTO);
            return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
        }
        log.debug("【END】 - function end ShopGoodsController - updateStatus 更新商品失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

    @ApiOperation(value = "上传商品图片")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "图片文件", required = true, dataType = "file"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_MENU_NAME,
            action = LogConstant.UPLOAD,
            operation = LogConstant.OPERATION_GOODS_UPLOAD)
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public JsonResult upload(@RequestPart("file") MultipartFile file){
        log.debug("【START】 - function upload");
        JsonResult jsonResult = FileUploadUtil.upload(file,filesConfig.getFormalPath(),FileUploadUrlEnum.GOODS_URL.getMsg());
        log.debug("【END】 - function upload");
        return jsonResult;
    }


}
