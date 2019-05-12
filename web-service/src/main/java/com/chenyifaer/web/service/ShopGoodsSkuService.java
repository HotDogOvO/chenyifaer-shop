package com.chenyifaer.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.dto.SkuDTO;
import com.chenyifaer.web.entity.po.ShopGoodsSkuPO;
import com.chenyifaer.web.entity.vo.GoodsSkuVO;
import com.chenyifaer.web.entity.vo.SkuKeyVO;
import com.chenyifaer.web.entity.vo.SkuValueVO;

import java.util.List;

/**
 * 分类管理 - sku商品表 服务类
 * @author wudh
 * @since 2019-05-10
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSkuPO> {

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
    List<SkuKeyVO> getKeyName();

    /**
     * 根据SkuKeyId查询SkuValue
     * @Author:wudh
     * @Date: 2019/5/12 22:18
     */
    List<SkuValueVO> getSkuValueBySkuKey(SkuDTO skuDTO);
}
