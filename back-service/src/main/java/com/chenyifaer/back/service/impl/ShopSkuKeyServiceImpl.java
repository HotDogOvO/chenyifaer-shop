package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.SkuKeyDTO;
import com.chenyifaer.back.entity.po.ShopSkuKeyPO;
import com.chenyifaer.back.dao.ShopSkuKeyDao;
import com.chenyifaer.back.entity.vo.SkuKeyVO;
import com.chenyifaer.back.service.ShopSkuKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类管理 - sku分类表 服务实现类
 * @author wudh
 * @since 2019-04-30
 */
@Service
public class ShopSkuKeyServiceImpl extends ServiceImpl<ShopSkuKeyDao, ShopSkuKeyPO> implements ShopSkuKeyService {

    @Autowired
    private ShopSkuKeyDao shopSkuKeyDao;

    @Override
    public List<SkuKeyVO> getList(SkuKeyDTO skuKeyDTO) {
        return this.shopSkuKeyDao.getList(skuKeyDTO);
    }
}
