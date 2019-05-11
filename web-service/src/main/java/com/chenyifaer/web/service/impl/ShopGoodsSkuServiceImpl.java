package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.po.ShopGoodsSkuPO;
import com.chenyifaer.web.dao.ShopGoodsSkuDao;
import com.chenyifaer.web.entity.vo.GoodsSkuVO;
import com.chenyifaer.web.service.ShopGoodsSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类管理 - sku商品表 服务实现类
 * @author wudh
 * @since 2019-05-10
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuDao, ShopGoodsSkuPO> implements ShopGoodsSkuService {

    @Autowired
    private ShopGoodsSkuDao shopGoodsSkuDao;

    @Override
    public List<GoodsSkuVO> getSkuByGoodsId(GoodsDTO goodsDTO) {
        return this.shopGoodsSkuDao.getSkuByGoodsId(goodsDTO);
    }
}
