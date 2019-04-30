package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.SkuValueDTO;
import com.chenyifaer.back.entity.po.ShopSkuValuePO;
import com.chenyifaer.back.dao.ShopSkuValueDao;
import com.chenyifaer.back.entity.vo.SkuValueTreeVO;
import com.chenyifaer.back.entity.vo.SkuValueVO;
import com.chenyifaer.back.service.ShopSkuValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类管理 - sku属性表 服务实现类
 * @author wudh
 * @since 2019-04-30
 */
@Service
public class ShopSkuValueServiceImpl extends ServiceImpl<ShopSkuValueDao, ShopSkuValuePO> implements ShopSkuValueService {

    @Autowired
    private ShopSkuValueDao shopSkuValueDao;

    @Override
    public List<SkuValueVO> getList(SkuValueDTO skuValueDTO) {
        return this.shopSkuValueDao.getList(skuValueDTO);
    }

    @Override
    public List<SkuValueTreeVO> getTreeList() {
        return this.shopSkuValueDao.getTreeList();
    }
}
