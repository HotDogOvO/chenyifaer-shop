package com.chenyifaer.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyifaer.web.entity.dto.GoodsRecommendedDTO;
import com.chenyifaer.web.entity.po.ShopGoodsPO;
import com.chenyifaer.web.dao.ShopGoodsDao;
import com.chenyifaer.web.entity.vo.GoodsRecommendedVO;
import com.chenyifaer.web.enums.GoodsRecommendedEnum;
import com.chenyifaer.web.enums.GoodsStatusEnum;
import com.chenyifaer.web.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.web.util.GetLimitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * 商品管理 - 商品表 服务实现类
 * @author wudh
 * @since 2019-05-07
 */
@Slf4j
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoodsPO> implements ShopGoodsService {

    @Autowired
    private ShopGoodsDao shopGoodsDao;

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
        int endSize = GetLimitUtil.getRecommendEndSize(startSize);
        log.debug("【RUN】 - function ShopGoodsServiceImpl - getRecommendedList - 计算出的limit起始数值为：{}，结束数值为：{}" , startSize,endSize);

        //插入查询条件
        GoodsRecommendedDTO goodsRecommendedDTO = new GoodsRecommendedDTO()
                .setRecommendedStatus(GoodsRecommendedEnum.TRUE.getCode())
                .setStatus(GoodsStatusEnum.STATUS_001.getCode())
                .setStartSize(startSize)
                .setEndSize(endSize);
        List<GoodsRecommendedVO> list = this.shopGoodsDao.getRecommendedList(goodsRecommendedDTO);
        log.debug("【END】 - function ShopGoodsServiceImpl - getRecommendedList - 查询的结果为：" + list);

        return list;
    }
}
