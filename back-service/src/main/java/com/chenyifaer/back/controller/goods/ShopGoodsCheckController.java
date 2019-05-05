package com.chenyifaer.back.controller.goods;


import com.chenyifaer.back.annotation.LogAnnotation;
import com.chenyifaer.back.annotation.RsaAnnotation;
import com.chenyifaer.back.constant.LogConstant;
import com.chenyifaer.back.entity.dto.GoodsCheckDTO;
import com.chenyifaer.back.entity.dto.GoodsDTO;
import com.chenyifaer.back.entity.po.ShopGoodsCheckPO;
import com.chenyifaer.back.entity.po.ShopGoodsPO;
import com.chenyifaer.back.entity.vo.GoodsCheckVO;
import com.chenyifaer.back.service.ShopGoodsCheckService;
import com.chenyifaer.back.service.ShopGoodsService;
import com.chenyifaer.basic.common.constant.JsonResult;
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
 * 商品管理 - 商品审核表 前端控制器
 * @author wudh
 * @since 2019-05-05
 */

@Slf4j
@RestController
@RequestMapping("/goods/check")
@Api(value = "商品管理",tags = {"商品管理 - 商品审核管理"})
public class ShopGoodsCheckController {

    @Autowired
    private ShopGoodsCheckService shopGoodsCheckService;

    @Autowired
    private ShopGoodsService shopGoodsService;

    @ApiOperation(value = "查询商品审核列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页码", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "当前页条数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "goodsName", value = "商品名", required = false, dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：通过审核 9：审核失败）", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public JsonResult list(@RequestBody @Validated GoodsCheckDTO goodsCheckDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsCheckController - list");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsCheckController - list 参数校验失败");
            return check;
        }
        PageHelper.startPage(goodsCheckDTO.getPageIndex(), goodsCheckDTO.getPageSize());
        List<GoodsCheckVO> list = this.shopGoodsCheckService.getList(goodsCheckDTO);
        PageInfo<GoodsCheckVO> pageList = new PageInfo<>(list);
        log.debug("【END】 - function end ShopGoodsCheckController - list 查询的结果为：" + list);
        return ResponseResult.Success(ResultCodeEnums.SUCCESS_001, pageList);
    }

    @ApiOperation(value = "审核商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "goodsCheckId", value = "商品ID", required = true, dataType = "int"),
        @ApiImplicitParam(name = "status", value = "状态（0：待审核 1：上架 2：下架 9：审核失败）", required = true, dataType = "int"),
        @ApiImplicitParam(name = "failRemark", value = "审核失败原因", required = false, dataType = "int"),
    })
    @RsaAnnotation
    @LogAnnotation(
            menuName = LogConstant.GOODS_CHECK_MENU_NAME,
            action = LogConstant.CHECK,
            operation = LogConstant.OPERATION_GOODS_CHECK)
    @RequestMapping(value = "/check" , method = RequestMethod.POST)
    public JsonResult check(@RequestBody @Validated(GoodsDTO.Update.class) GoodsCheckDTO goodsCheckDTO , BindingResult br) {
        log.debug("【START】 - function ShopGoodsController - check");
        JsonResult check = CheckUtil.check(br);
        if (check != null) {
            log.error("【ERROR】 - function ShopGoodsController - check 参数校验失败");
            return check;
        }

        boolean flag = this.shopGoodsCheckService.updateById(new ShopGoodsCheckPO()
                .setGoodsCheckId(goodsCheckDTO.getGoodsCheckId())
                .setStatus(goodsCheckDTO.getStatus())
                .setFailRemark(goodsCheckDTO.getFailRemark())
                .setCheckTime(DateUtil.getTime()));
        if(flag){
            flag = this.shopGoodsService.updateById(new ShopGoodsPO()
                    .setGoodsId(goodsCheckDTO.getGoodsId())
                    .setStatus(goodsCheckDTO.getStatus()));
            if(flag){
                log.debug("【END】 - function end ShopGoodsController - check 审核商品成功，更新内容为："+ goodsCheckDTO);
                return ResponseResult.Success(ResultCodeEnums.SUCCESS_003);
            }
            log.debug("【END】 - function end ShopGoodsController - check 审核商品失败");
            return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
        }
        log.debug("【END】 - function end ShopGoodsController - check 审核商品失败");
        return ResponseResult.Fail(ResultCodeEnums.FAIL_10003);
    }

}
