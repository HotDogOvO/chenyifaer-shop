package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.po.ShopGoodsPO;
import com.chenyifaer.web.entity.vo.*;

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
 * 商品管理 - 商品表 Mapper 接口
 * @author wudh
 * @since 2019-05-07
 */
public interface ShopGoodsDao extends BaseMapper<ShopGoodsPO> {

    /**
     * 根据商品分类查询商品
     * @Author:wudh
     * @Date: 2019/5/8 17:13
     */
    List<GoodsByTypeVO> getGoodsByType(GoodsDTO goodsDTO);

    /**
     * 查询首页推荐商品
     * @Author:wudh
     * @Date: 2019/5/7 22:11
     */
    List<GoodsRecommendedVO> getRecommendedList(GoodsDTO goodsDTO);

    /**
     * 查询首页支持优惠券的商品
     * @Author:wudh
     * @Date: 2019/5/8 15:33
     */
    List<GoodsCouponsVO> getCouponsList(GoodsDTO goodsDTO);

    /**
     * 查询首页支持积分的商品
     * @Author:wudh
     * @Date: 2019/5/8 15:34
     */
    List<GoodsIntegralVO> getIntegralList(GoodsDTO goodsDTO);

    /**
     * 根据商品ID查询商品详情
     * @Author:wudh
     * @Date: 2019/5/10 16:30
     */
    List<GoodsDetailVO> getDetail(GoodsDTO goodsDTO);

    /**
     * 根据销量查询商品
     * @Author:wudh
     * @Date: 2019/5/10 17:08
     */
    List<GoodsSalesVO> getGoodsBySales(GoodsDTO goodsDTO);

    /**
     * 根据SKU查询商品
     * @Author:wudh
     * @Date: 2019/5/11 20:55
     */
    List<GoodsBySkuVO> getGoodsBySku(GoodsDTO goodsDTO);
}
