package com.chenyifaer.web.service.impl;

import com.chenyifaer.web.entity.dto.ShopCartDTO;
import com.chenyifaer.web.entity.po.ShopCartPO;
import com.chenyifaer.web.dao.ShopCartDao;
import com.chenyifaer.web.entity.vo.ShopCartVO;
import com.chenyifaer.web.enums.GoodsImgEnum;
import com.chenyifaer.web.service.ShopCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 - 购物车表 服务实现类
 * @author wudh
 * @since 2019-05-11
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartDao, ShopCartPO> implements ShopCartService {

    @Autowired
    private ShopCartDao shopCartDao;

    @Override
    public List<ShopCartVO> getList(ShopCartDTO shopCartDTO) {
        shopCartDTO.setType(GoodsImgEnum.IMG_TYPE_001.getCode());
        return this.shopCartDao.getList(shopCartDTO);
    }
}
