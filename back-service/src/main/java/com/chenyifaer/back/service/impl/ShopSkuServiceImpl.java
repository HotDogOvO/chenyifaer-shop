package com.chenyifaer.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyifaer.back.dao.ShopSkuDao;
import com.chenyifaer.back.entity.po.ShopSkuPO;
import com.chenyifaer.back.entity.vo.GoodsSkuTreeVO;
import com.chenyifaer.back.service.ShopSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类管理 - sku分类属性表 服务实现类
 * @author wudh
 * @since 2019-04-30
 */
@Service
public class ShopSkuServiceImpl extends ServiceImpl<ShopSkuDao, ShopSkuPO> implements ShopSkuService {

    @Autowired
    private ShopSkuDao shopSkuDao;

    @Override
    public List<GoodsSkuTreeVO> getGoodsSkuTree() {
        return this.shopSkuDao.getGoodsSkuTree();
    }
}
