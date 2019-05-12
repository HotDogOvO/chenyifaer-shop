package com.chenyifaer.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.dto.SkuDTO;
import com.chenyifaer.web.entity.po.ShopGoodsSkuPO;
import com.chenyifaer.web.entity.vo.GoodsSkuVO;
import com.chenyifaer.web.entity.vo.SkuKeyVO;
import com.chenyifaer.web.entity.vo.SkuValueVO;

import java.util.List;

/**
 * 分类管理 - sku商品表 Mapper 接口
 * @author wudh
 * @since 2019-05-10
 */
public interface ShopGoodsSkuDao extends BaseMapper<ShopGoodsSkuPO> {

    /**
     * 根据商品ID查询SKU
     * @Author:wudh
     * @Date: 2019/5/10 16:44
     */
    List<GoodsSkuVO> getSkuByGoodsId(GoodsDTO goodsDTO);

    /**
     * 随机查询Key名称
     * @Author:wudh
     * @Date: 2019/5/12 21:45
     */
    List<SkuKeyVO> getKeyName(SkuDTO skuDTO);

    /**
     * 根据SkuKeyId查询SkuValue
     * @Author:wudh
     * @Date: 2019/5/12 22:18
     */
    List<SkuValueVO> getSkuValueBySkuKey(SkuDTO skuDTO);

}
