package com.chenyifaer.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.web.dao.ShopGoodsSkuDao;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.po.ShopGoodsPO;
import com.chenyifaer.web.dao.ShopGoodsDao;
import com.chenyifaer.web.entity.vo.*;
import com.chenyifaer.web.enums.*;
import com.chenyifaer.web.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.web.util.GetLimitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 商品管理 - 商品表 服务实现类
 * @author wudh
 * @since 2019-05-07
 */
@Slf4j
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoodsPO> implements ShopGoodsService {

    @Autowired
    private ShopGoodsDao shopGoodsDao;

    @Autowired
    private ShopGoodsSkuDao shopGoodsSkuDao;

    @Override
    public List<GoodsByTypeVO> getGoodsByType(GoodsDTO goodsDTO) {
        log.debug("【START】 - function ShopGoodsServiceImpl - getGoodsByType");
        //查询6条
        goodsDTO.setStartSize(SystemConstant.LIMIT_START_SIZE).setEndSize(SystemConstant.GOODS_TYPE_SIZE);

        List<GoodsByTypeVO> list = this.shopGoodsDao.getGoodsByType(goodsDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getGoodsByType");
        return list;
    }

    @Override
    public List<GoodsByTypeVO> getRecommendGoodsByType(GoodsDTO goodsDTO) {
        log.debug("【START】 - function ShopGoodsServiceImpl - getRecommendGoodsByType");
        //查询1条推荐商品
        goodsDTO.setStartSize(SystemConstant.LIMIT_START_SIZE)
                .setEndSize(SystemConstant.GOODS_RECOMMENDED_TYPE_SIZE)
                .setRecommendedStatus(GoodsRecommendedEnum.TRUE.getCode());

        List<GoodsByTypeVO> list = this.shopGoodsDao.getGoodsByType(goodsDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getRecommendGoodsByType");
        return list;
    }

    @Override
    public List<GoodsRecommendedVO> getRecommendedList() {
        log.debug("【START】 - function ShopGoodsServiceImpl - getRecommendedList");

        //查询商品状态为上架且为推荐的商品数量
        int count = this.shopGoodsDao.selectCount(new QueryWrapper<>(new ShopGoodsPO()
                .setRecommendedStatus(GoodsRecommendedEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())));
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getRecommendedList - 查询出符合条件的商品个数为：{}个" , count);

        //计算limit数值
        int startSize = GetLimitUtil.getRecommendStartSize(count);
        int endSize = SystemConstant.GOODS_RECOMMENDED_SIZE;
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getRecommendedList - 计算出的limit起始数值为：{}，结束数值为：{}" , startSize,endSize);

        //插入查询条件
        GoodsDTO goodsDTO = new GoodsDTO()
                .setRecommendedStatus(GoodsRecommendedEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())
                .setStartSize(startSize)
                .setEndSize(endSize);
        List<GoodsRecommendedVO> list = this.shopGoodsDao.getRecommendedList(goodsDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getRecommendedList - 查询的结果为：" + list);

        return list;
    }

    @Override
    public List<GoodsCouponsVO> getCouponsList() {
        log.debug("【START】 - function ShopGoodsServiceImpl - getCouponsList");

        //查询商品状态为上架且为支持优惠券的商品数量
        int count = this.shopGoodsDao.selectCount(new QueryWrapper<>(new ShopGoodsPO()
                .setCouponsStatus(GoodsCouponsEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())));
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getCouponsList - 查询出符合条件的商品个数为：{}个" , count);

        //计算limit数值
        int startSize = GetLimitUtil.getCouponsStartSize(count);
        int endSize = SystemConstant.GOODS_COUPONS_SIZE;
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getCouponsList - 计算出的limit起始数值为：{}，结束数值为：{}" , startSize,endSize);

        //插入查询条件
        GoodsDTO goodsDTO = new GoodsDTO()
                .setCouponsStatus(GoodsCouponsEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())
                .setStartSize(startSize)
                .setEndSize(endSize);
        List<GoodsCouponsVO> list = this.shopGoodsDao.getCouponsList(goodsDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getCouponsList - 查询的结果为：" + list);

        return list;
    }

    @Override
    public List<GoodsIntegralVO> getIntegralList() {
        log.debug("【START】 - function ShopGoodsServiceImpl - getIntegralList");

        //查询商品状态为上架且为支持积分的商品数量
        int count = this.shopGoodsDao.selectCount(new QueryWrapper<>(new ShopGoodsPO()
                .setIntegralStatus(GoodsCouponsEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())));
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getIntegralList - 查询出符合条件的商品个数为：{}个" , count);

        //计算limit数值
        int startSize = GetLimitUtil.getIntegralStartSize(count);
        int endSize = SystemConstant.GOODS_INTEGRAL_SIZE;
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getIntegralList - 计算出的limit起始数值为：{}，结束数值为：{}" , startSize,endSize);

        //插入查询条件
        GoodsDTO goodsDTO = new GoodsDTO()
                .setIntegralStatus(GoodsIntegralEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())
                .setStartSize(startSize)
                .setEndSize(endSize);
        List<GoodsIntegralVO> list = this.shopGoodsDao.getIntegralList(goodsDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getIntegralList - 查询的结果为：" + list);

        return list;
    }

    @Override
    public List<GoodsDetailReturnVO> getDetail(GoodsDTO goodsDTO) {
        log.debug("【START】 - function ShopGoodsServiceImpl - getDetail");
        List<GoodsDetailReturnVO> list = new ArrayList<>();
        List<GoodsDetailVO> goodsList = this.shopGoodsDao.getDetail(goodsDTO);
        List<GoodsSkuVO> skuList = this.shopGoodsSkuDao.getSkuByGoodsId(goodsDTO);

        list.add(new GoodsDetailReturnVO().setGoodsList(goodsList).setSkuList(skuList));
        log.debug("【END】 - function ShopGoodsServiceImpl - getDetail");
        return list;
    }

    @Override
    public List<GoodsSalesVO> getGoodsBySales() {
        log.debug("【START】 - function ShopGoodsServiceImpl - getGoodsBySales");

        int count = this.shopGoodsDao.selectCount(new QueryWrapper<>());
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getGoodsBySales，查询出的商品数量为【{}】", count);

        int startSize = GetLimitUtil.getSalesGoodsStartSize(count);
        int endSize = SystemConstant.GOODS_SALES_SIZE;
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getGoodsBySales，计算出的起始数值是：【{}】，结束数值是：【{}】", startSize , endSize);

        List<GoodsSalesVO> list = this.shopGoodsDao.getGoodsBySales(new GoodsDTO()
                .setType(GoodsImgEnum.IMG_TYPE_001.getCode())
                .setStartSize(startSize)
                .setEndSize(endSize));

        log.debug("【END】 - function ShopGoodsServiceImpl - getGoodsBySales");
        return list;
    }

}
