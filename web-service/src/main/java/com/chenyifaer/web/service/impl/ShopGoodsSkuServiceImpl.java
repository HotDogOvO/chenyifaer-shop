package com.chenyifaer.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.basic.common.constant.SystemConstant;
import com.chenyifaer.web.dao.ShopGoodsSkuDao;
import com.chenyifaer.web.dao.ShopSkuKeyDao;
import com.chenyifaer.web.entity.dto.GoodsDTO;
import com.chenyifaer.web.entity.dto.SkuDTO;
import com.chenyifaer.web.entity.po.ShopGoodsSkuPO;
import com.chenyifaer.web.entity.vo.GoodsSkuVO;
import com.chenyifaer.web.entity.vo.SkuKeyVO;
import com.chenyifaer.web.entity.vo.SkuValueVO;
import com.chenyifaer.web.service.ShopGoodsSkuService;
import com.chenyifaer.web.service.ShopSkuKeyService;
import com.chenyifaer.web.util.GetLimitUtil;
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

    @Autowired
    private ShopSkuKeyDao shopSkuKeyDao;

    @Override
    public List<GoodsSkuVO> getSkuByGoodsId(GoodsDTO goodsDTO) {
        return this.shopGoodsSkuDao.getSkuByGoodsId(goodsDTO);
    }

    @Override
    public List<SkuKeyVO> getKeyName() {
        int count = this.shopSkuKeyDao.selectCount(new QueryWrapper<>());
        int startSize = GetLimitUtil.getSkuKeyStartSize(count);
        SkuDTO skuDTO = new SkuDTO().setStartSize(startSize).setEndSize(SystemConstant.SKU_KEY_SIZE);
        return this.shopGoodsSkuDao.getKeyName(skuDTO);
    }

    @Override
    public List<SkuValueVO> getSkuValueBySkuKey(SkuDTO skuDTO) {
        return this.shopGoodsSkuDao.getSkuValueBySkuKey(skuDTO);
    }
}
