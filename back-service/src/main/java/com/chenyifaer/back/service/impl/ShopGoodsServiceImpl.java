package com.chenyifaer.back.service.impl;

import com.chenyifaer.back.entity.dto.GoodsDTO;
import com.chenyifaer.back.entity.po.ShopGoodsPO;
import com.chenyifaer.back.dao.ShopGoodsDao;
import com.chenyifaer.back.entity.vo.GoodsVO;
import com.chenyifaer.back.service.ShopGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 - 商品表 服务实现类
 * @author wudh
 * @since 2019-04-25
 */
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsDao, ShopGoodsPO> implements ShopGoodsService {

    @Autowired
    private ShopGoodsDao shopGoodsDao;

    @Override
    public List<GoodsVO> getList(GoodsDTO goodsDTO) {
        return this.shopGoodsDao.getList(goodsDTO);
    }
}
